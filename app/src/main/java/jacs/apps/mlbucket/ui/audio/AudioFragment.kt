package jacs.apps.mlbucket.ui.audio

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import jacs.apps.mlbucket.R
import jacs.apps.mlbucket.databinding.AudioFragmentBinding
import java.nio.ByteBuffer


private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
class AudioFragment : Fragment() {

    companion object {
        fun newInstance() = AudioFragment()
    }

    var BufferElements2Rec = 1024 // want to play 2048 (2K) since 2 bytes we use only 1024

    var BytesPerElement = 2 // 2 bytes in 16bit format

    private val RECORDER_SAMPLERATE = 8000
    private val RECORDER_CHANNELS: Int = AudioFormat.CHANNEL_IN_MONO
    private val RECORDER_AUDIO_ENCODING: Int = AudioFormat.ENCODING_PCM_16BIT
    private var recorder: AudioRecord? = null
    private var isRecording = false;
    private lateinit var viewModel: AudioViewModel
    private lateinit var binding: AudioFragmentBinding
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    private var permissionToRecordAccepted = false
    private lateinit var recordButton : Button
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }

    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AudioFragmentBinding.inflate(inflater)
        recordButton = binding.record
        recorder = AudioRecord.Builder()
            .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(AudioFormat.ENCODING_PCM_8BIT)
                    .setSampleRate(16000)
                    .setChannelMask(AudioFormat.CHANNEL_IN_MONO)
                    .build()
            )
            .setBufferSizeInBytes(BufferElements2Rec * BytesPerElement)
            .build().also { recorder = it }

        recordButton.setOnClickListener {
            Log.d("permission",permissionToRecordAccepted.toString())
            if(permissionToRecordAccepted){
                if(isRecording){
                    recorder!!.stop()
                    isRecording = false;
                    recordButton.text = getString(R.string.record_audio)
                }else{
                    recorder!!.startRecording();
                    isRecording = true
                    recordButton.text = getString(R.string.stop)
                    recordingStuff()
                }
            }else{
                ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION)

            }


        }
        return binding.root
    }
    fun recordingStuff(){

        while(isRecording){
            recorder?.read(viewModel.byteBuffer!!, BufferElements2Rec);
            viewModel.runModel(viewModel.byteBuffer)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        permissionToRecordAccepted = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED

        ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        viewModel = ViewModelProvider(this).get(AudioViewModel::class.java)
        viewModel.byteBuffer = ByteBuffer.allocateDirect(BytesPerElement * BufferElements2Rec)

    }

}