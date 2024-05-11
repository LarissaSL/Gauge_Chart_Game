package com.example.gauge;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.ekn.gruzer.gaugelibrary.Range;

public class MainActivity extends AppCompatActivity {

    private HalfGauge pcGauge, jogadorGauge;
    private ProgressBar pcProgressBar, jogadorProgressBar;
    private Button btnAvancar, btnVoltar;
    private TextView txtVencedor, txtKmPc, txtKmJogador, txtPc;

    private int pcProgress = 0;
    private int jogadorProgress = 0;
    private int pcLevel = 0;
    private Handler handler = new Handler();
    private boolean jogoResetando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarViews();
        iniciarGauge();
        iniciarEventosDeClick();

        // Iniciando o progresso automatico do PC
        handler.post(pcProgressRunnable);
    }

    private void iniciarViews() {
        pcGauge = findViewById(R.id.pcGauge);
        jogadorGauge = findViewById(R.id.jogadorGauge);
        pcProgressBar = findViewById(R.id.pcProgressBar);
        jogadorProgressBar = findViewById(R.id.jogadorProgressBar);
        btnAvancar = findViewById(R.id.btnAvancar);
        btnVoltar = findViewById(R.id.btnVoltar);
        txtVencedor = findViewById(R.id.txtVencedor);
        txtKmPc = findViewById(R.id.txtKmPc);
        txtKmJogador = findViewById(R.id.txtKmJogador);
        txtPc = findViewById(R.id.txtPc);

        pcProgressBar.setMax(100);
        jogadorProgressBar.setMax(100);
    }

    private void iniciarGauge() {
        defineFaixasDeCores(pcGauge);
        defineFaixasDeCores(jogadorGauge);
    }

    private void iniciarEventosDeClick() {
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avancar();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private Runnable pcProgressRunnable = new Runnable() {
        @Override
        public void run() {
            if (pcProgress < 100) {
                pcProgress += getTamanhoPassoPC();
                updateProgressoPC();
                handler.postDelayed(this, 1000);
            } else {
                if (jogadorProgress < 100) {
                    txtVencedor.setText("Vencedor: PC!");
                    mostrarToastVencedor("Eita...vamos de novo!");
                    pcLevel = 0;
                    atualizarNivelPC();
                    resetJogo();
                } else {
                    txtVencedor.setText("Vencedor: Jogador!");
                    if (pcLevel == 2) {
                        pcLevel = 0;
                    } else {
                        pcLevel++;
                    }
                    resetJogo();
                }
            }
        }
    };

    private void avancar() {
        if (!jogoResetando) {
            jogadorProgress += 5;
            if (jogadorProgress > 100) {
                jogadorProgress = 100;
            }
            updateProgressoJogador();
            verificarVencedor();
        }
    }

    private void voltar() {
        if (!jogoResetando) {
            jogadorProgress -= 5;
            if (jogadorProgress < 0) {
                jogadorProgress = 0;
            }
            updateProgressoJogador();
        }
    }

    private void updateProgressoPC() {
        pcProgress = Math.min(pcProgress, 100);
        pcProgressBar.setProgress(pcProgress);
        pcGauge.setValue(pcProgress);
        txtKmPc.setText(String.valueOf(pcProgress) + " KM");
    }

    private void updateProgressoJogador() {
        jogadorProgressBar.setProgress(jogadorProgress);
        jogadorGauge.setValue(jogadorProgress);
        txtKmJogador.setText(String.valueOf(jogadorProgress) + " KM");
    }

    private void verificarVencedor() {
        if (pcProgress >= 100 && jogadorProgress < 100) {
            txtVencedor.setText("Vencedor: PC!");
            this.pcLevel = 0;
            atualizarNivelPC();
            mostrarToastVencedor("Eita...vamos de novo!");
            resetJogo();
        } else if (jogadorProgress >= 100) {
            txtVencedor.setText("Vencedor: Jogador!");
            if (this.pcLevel == 2) {
                pcLevel = 0;
            } else {
                pcLevel++;
            }
            atualizarNivelPC();
            mostrarToastVencedor("Vamboraa");
            resetJogo();
        }
    }

    private void resetJogo() {
        jogoResetando = true;
        pcProgress = 0;
        updateProgressoPC();
        jogadorProgress = 0;
        updateProgressoJogador();
        handler.removeCallbacks(pcProgressRunnable);
        txtVencedor.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtVencedor.setText("");
                handler.post(pcProgressRunnable);
                jogoResetando = false;
            }
        }, 4000);
    }

    private void defineFaixasDeCores(HalfGauge gauge) {
        Range range1 = new Range();
        range1.setColor(Color.parseColor("#ce0000"));
        range1.setFrom(0.0);
        range1.setTo(40.0);

        Range range2 = new Range();
        range2.setColor(Color.parseColor("#E3E500"));
        range2.setFrom(40.0);
        range2.setTo(70.0);

        Range range3 = new Range();
        range3.setColor(Color.parseColor("#00b20b"));
        range3.setFrom(70.0);
        range3.setTo(100.0);

        gauge.addRange(range1);
        gauge.addRange(range2);
        gauge.addRange(range3);

        gauge.setMinValue(0.0);
        gauge.setMaxValue(100.0);
        gauge.setValue(0.0);
    }

    private void mostrarToastVencedor(String mensagem) {
        Toast toast = Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void atualizarNivelPC() {
        switch (pcLevel) {
            case 1:
                txtPc.setText("Player: PC Moderado");
                break;
            case 2:
                txtPc.setText("Player: PC Radiante");
                break;
            default:
                txtPc.setText("Player: PC");
                break;
        }
    }

    private int getTamanhoPassoPC() {
        switch (pcLevel) {
            case 0:
                return 5;
            case 1:
                return 10;
            case 2:
                return 25;
            default:
                return 5;
        }
    }
}
