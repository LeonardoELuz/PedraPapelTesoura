package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivitySettingsBinding
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model.ConfiguracaoFirebase

class SettingsActivity : AppCompatActivity() {
    private lateinit var activitySettingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_settings)
        setContentView(activitySettingsBinding.root)

        activitySettingsBinding.salvarBt.setOnClickListener {
            val numeroJogadores: Int = (activitySettingsBinding.numeroJogadoresSp.selectedView as TextView).text.toString().toInt()
            val configuracao =  Configuracao(numeroJogadores)
            val retornoIntent = Intent()
            retornoIntent.putExtra(Intent.EXTRA_USER, configuracao)
            setResult(RESULT_OK, retornoIntent)
            val configuracaoDatabase = ConfiguracaoFirebase()
            configuracaoDatabase.criaOuAtualizaConfiguracao(numeroJogadores)
            finish()
        }
    }

}