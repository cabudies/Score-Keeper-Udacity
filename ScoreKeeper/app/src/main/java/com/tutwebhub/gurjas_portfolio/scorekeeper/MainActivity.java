package com.tutwebhub.gurjas_portfolio.scorekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String team_one_name, team_two_name;
    int number_of_players_int;
    private EditText team_one, team_two, number_of_players;
    private Button lets_play, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize views i.e. EditText, Buttons;
        team_one = (EditText) findViewById(R.id.name_team_one);
        team_two = (EditText) findViewById(R.id.name_team_two);
        number_of_players = (EditText)findViewById(R.id.number_of_players);
        lets_play = (Button) findViewById(R.id.lets_play_cricket);
        reset = (Button) findViewById(R.id.game_reset);

        //set on Click listener on Buttons.
        lets_play.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.lets_play_cricket: getInputFromUser();
                break;

            case R.id.game_reset: resetAllEditText();
                break;
        }
    }

    private void getInputFromUser()
    {
        boolean startActivityIntent = true;
        team_one_name = team_one.getText().toString();
        team_two_name = team_two.getText().toString();
        String players_count = number_of_players.getText().toString();
        number_of_players_int = !players_count.equals("")?Integer.parseInt(players_count) : 0;

        if (team_one_name.isEmpty() && team_two_name.isEmpty() && number_of_players_int == 0)
        {
            Toast.makeText(this, "Please enter match details", Toast.LENGTH_LONG).show();
            startActivityIntent = false;
        }

        else if (team_one_name.isEmpty())
        {
            Toast.makeText(this, "Please enter Team One name", Toast.LENGTH_LONG).show();
            startActivityIntent = false;
        }
        else if (team_two_name.isEmpty())
        {
            Toast.makeText(this, "Please enter Team Two name", Toast.LENGTH_LONG).show();
            startActivityIntent = false;
        }
        else if (number_of_players_int == 0)
        {
            Toast.makeText(this, "Please enter minimum 1 player", Toast.LENGTH_LONG).show();
            startActivityIntent = false;
        }

        else if (startActivityIntent)
        {
            letsPlayActivity();
        }
    }

    private void letsPlayActivity()
    {
        // intialize intent variable with the LetsPlay class.
        Intent letsPlayActivity = new Intent(this, LetsPlay.class);
        // pass team names, number of players who will play.
        letsPlayActivity.putExtra("team_one_name", team_one_name);
        letsPlayActivity.putExtra("team_two_name", team_two_name);
        letsPlayActivity.putExtra("numbers_of_players", number_of_players_int);
        // start activity.
        startActivity(letsPlayActivity);
    }

    private void resetAllEditText()
    {
        team_one.setText("");
        team_two.setText("");
        number_of_players.setText("");

        team_one.setHint("Team 1");
        team_two.setHint("Team 2");
        number_of_players.setHint("Number of Players");
    }
}
