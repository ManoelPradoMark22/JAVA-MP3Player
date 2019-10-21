package mediaplayer.cursoandroid.com.mediaplayer;

import android.app.Activity;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView botaoTocar;
    private MediaPlayer mediaPlayer;
    private ImageView botaoStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoTocar = (ImageView) findViewById(R.id.botaoTocarId);
        //recebe dois atributos. Primeiro: contexto. segundo: o id da musica
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.robbers);
        botaoStop = (ImageView) findViewById(R.id.botaoStopId);

         botaoTocar.setImageResource(R.drawable.playpng);
        botaoStop.setImageResource(R.drawable.stoppng);

        botaoTocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){ //verifica se a midia está tocando
                    pausarMusica();
                }else{
                    tocarMusica();
                }

            }
        });

        botaoStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    pararMusica();
                    botaoTocar.setImageResource(R.drawable.playpng);
                }
            }
        });
    }

    private void tocarMusica (){
        if (mediaPlayer!=null){
            mediaPlayer.start(); //toca
            botaoTocar.setImageResource(R.drawable.pausepng);
        }
    }

    private void pausarMusica (){
        if (mediaPlayer!=null){
            mediaPlayer.pause(); //pausa
            //.stop()  para a musica
            botaoTocar.setImageResource(R.drawable.playpng);
        }
    }

    private void pararMusica (){
        if (mediaPlayer!=null){
            mediaPlayer.stop(); //para
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.robbers);
        }
    }

    //é bom colocar esse onDestroy para liberar os recursos utilizados pelo MediaPlayer
    @Override
    protected void onDestroy() {

        if (mediaPlayer!=null && mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release(); //liberar os rescursos
                mediaPlayer = null;
        }
        //se nao aparecer o super, faça manualmente
        super.onDestroy();
    }

}
