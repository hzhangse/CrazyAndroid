package com.train.speech;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.train.R;

public class Speech extends Activity
{
	TextToSpeech tts;
	EditText editText;
	Button speech;
	Button record;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speech);
		// ��ʼ��TextToSpeech����
		tts = new TextToSpeech(this, new OnInitListener()
		{
			@Override
			public void onInit(int status)
			{
				// ���װ��TTS����ɹ�
				if (status == TextToSpeech.SUCCESS)
				{
					// ����ʹ����ʽӢ���ʶ�
					int result = tts.setLanguage(Locale.CHINESE);
					// �����֧�������õ�����
					if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
						&& result != TextToSpeech.LANG_AVAILABLE)
					{
						Toast.makeText(Speech.this, "TTS��ʱ��֧���������Ե��ʶ���", 50000)
							.show();
					}
				}
			}

		});
		editText = (EditText) findViewById(R.id.txt);
		speech = (Button) findViewById(R.id.speech);
		record = (Button) findViewById(R.id.record);
		speech.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ִ���ʶ�
				tts.speak(editText.getText().toString(),
					TextToSpeech.QUEUE_ADD, null);
			}
		});
		record.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ���ʶ��ı�����Ƶ��¼��ָ���ļ�
				tts.synthesizeToFile(editText.getText().toString(), null,
					"/mnt/sdcard/sound.wav");
				Toast.makeText(Speech.this, "������¼�ɹ���", 50000).show();
			}
		});
	}
	@Override
	public void onDestroy()
	{
		// �ر�TextToSpeech����
		if (tts != null)
		{
			tts.shutdown();
		}
	}
}