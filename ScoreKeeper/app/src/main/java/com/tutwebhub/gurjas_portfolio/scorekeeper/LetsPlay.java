package com.tutwebhub.gurjas_portfolio.scorekeeper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;
import static android.R.id.edit;

/**
 * Created by gurjas on 14-06-2017.
 */

public class LetsPlay extends AppCompatActivity implements View.OnClickListener{

    private String name_team_one, name_team_two; // team details
    private int number_of_players, setDeclareTeam1, setDeclareTeam2 = 0;                          // number of players
    private TextView displayNameTeam1, displayNameTeam2;        // display teams name
    private Button sixTeam1, fourTeam1, threeTeam1, twoTeam1, oneTeam1, outTeam1, declareTeam1;     // buttons for team 1
    private Button sixTeam2, fourTeam2, threeTeam2, twoTeam2, oneTeam2, outTeam2, declareTeam2;     // buttons for team 2
    //private int totalScoreTeam1, totalScoreTeam2, outPlayersTeam1, outPlayersTeam2 = 0;
    private TextView displayScoreTeam1, displayScoreTeam2, displayPlayersOutTeam1, displayPlayersOutTeam2;
    private int scoreTeam1, scoreTeam2, playersOutTeam1, playersOutTeam2 = 0;
    private boolean setTeam1Disabled, setTeam2Disabled = false;
    private Button gameResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lets_play_activity);

        // receive match details like team name from intent.
        Bundle extraValues = getIntent().getExtras();
        name_team_one = extraValues.getString("team_one_name"); // first team name
        name_team_two = extraValues.getString("team_two_name"); // second team name
        number_of_players = extraValues.getInt("numbers_of_players"); // players count

        // initialize EditTextField, Buttons,
        displayNameTeam1 = (TextView) findViewById(R.id.text_view_team_one);
        displayNameTeam2 = (TextView) findViewById(R.id.text_view_team_two);
        displayNameTeam1.setText(name_team_one);
        displayNameTeam2.setText(name_team_two);

        // initialization of buttons
        sixTeam1 = (Button) findViewById(R.id.SixTeam1);
        sixTeam2 = (Button) findViewById(R.id.SixTeam2);
        fourTeam1 = (Button) findViewById(R.id.FourTeam1);
        fourTeam2 = (Button) findViewById(R.id.FourTeam2);
        threeTeam1 = (Button) findViewById(R.id.ThreeTeam1);
        threeTeam2 = (Button) findViewById(R.id.ThreeTeam2);
        twoTeam1 = (Button) findViewById(R.id.TwoTeam1);
        twoTeam2 = (Button) findViewById(R.id.TwoTeam2);
        oneTeam1 = (Button) findViewById(R.id.OneTeam1);
        oneTeam2 = (Button) findViewById(R.id.OneTeam2);
        outTeam1 = (Button) findViewById(R.id.OutTeam1);
        outTeam2 = (Button) findViewById(R.id.OutTeam2);
        declareTeam1 = (Button) findViewById(R.id.DeclareTeam1);
        declareTeam2 = (Button) findViewById(R.id.DeclareTeam2);
        gameResult = (Button) findViewById(R.id.game_results);

        // set on click listener to this.
        sixTeam1.setOnClickListener(this);
        sixTeam2.setOnClickListener(this);
        fourTeam1.setOnClickListener(this);
        fourTeam2.setOnClickListener(this);
        threeTeam1.setOnClickListener(this);
        threeTeam2.setOnClickListener(this);
        twoTeam1.setOnClickListener(this);
        twoTeam2.setOnClickListener(this);
        oneTeam1.setOnClickListener(this);
        oneTeam2.setOnClickListener(this);
        outTeam1.setOnClickListener(this);
        outTeam2.setOnClickListener(this);
        declareTeam1.setOnClickListener(this);
        declareTeam2.setOnClickListener(this);
        gameResult.setOnClickListener(this);

        // initialise text view to display score, out
        displayScoreTeam1 = (TextView)findViewById(R.id.score_team_one);
        displayScoreTeam2 = (TextView) findViewById(R.id.score_team_two);
        displayPlayersOutTeam1 = (TextView) findViewById(R.id.out_team_one);
        displayPlayersOutTeam2 = (TextView) findViewById(R.id.out_team_two);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.SixTeam1 : addScore(1, 6);
                break;
            case R.id.SixTeam2 : addScore(2, 6);
                break;
            case R.id.FourTeam1 : addScore(1, 4);
                break;
            case R.id.FourTeam2 : addScore(2, 4);
                break;
            case R.id.ThreeTeam1 : addScore(1, 3);
                break;
            case R.id.ThreeTeam2 : addScore(2, 3);
                break;
            case R.id.TwoTeam1 : addScore(1, 2);
                break;
            case R.id.TwoTeam2 : addScore(2, 2);
                break;
            case R.id.OneTeam1 : addScore(1, 1);
                break;
            case R.id.OneTeam2 : addScore(2, 1);
                break;
            case R.id.OutTeam1 : teamPlayerOut( 1 );
                break;
            case R.id.OutTeam2 : teamPlayerOut( 2 );
                break;
            case R.id.DeclareTeam1 : teamDeclared( 1 );
                break;
            case R.id.DeclareTeam2 : teamDeclared( 2 );
                break;
            case R.id.game_results : displayGameResult();
                break;
        }
    }

    private void addScore(int team, int score)
    {
        if (playersOutTeam1<number_of_players && team == 1)
        {
            scoreTeam1 = scoreTeam1 + score;
            displayScoreTeam1.setText(""+scoreTeam1);
        }
        else if (playersOutTeam2<number_of_players && team == 2)
        {
            scoreTeam2 = scoreTeam2 + score;
            displayScoreTeam2.setText(""+scoreTeam2);
        }
    }

    private void teamPlayerOut(int team)
    {
        if (playersOutTeam1<number_of_players && team == 1 && setTeam1Disabled==false)
        {
            playersOutTeam1 = playersOutTeam1+1;
            displayPlayersOutTeam1.setText(""+playersOutTeam1);
            if (playersOutTeam1 == number_of_players)
            {
                Toast.makeText(this, "All the players of Team 1 are out", Toast.LENGTH_LONG).show();
                teamDeclared(team);
            }
        }
        else if (playersOutTeam2<number_of_players && team == 2 && setTeam2Disabled==false)
        {
            playersOutTeam2 = playersOutTeam2+1;
            displayPlayersOutTeam2.setText(""+playersOutTeam2);
            if (playersOutTeam2 == number_of_players)
            {
                Toast.makeText(this, "All the players of Team 2 are out", Toast.LENGTH_LONG).show();
                teamDeclared(team);
            }
        }
    }

    private void teamDeclared(int team)
    {
        if (team == 1)
        {
            setTeam1Disabled = true;
            disableTeams(team);
        }
        else
        {
            setTeam2Disabled = true;
            disableTeams(team);
        }
    }

    private void disableTeams(int team)
    {
        if (team == 1)
        {
            sixTeam1.setEnabled(false);
            fourTeam1.setEnabled(false);
            threeTeam1.setEnabled(false);
            twoTeam1.setEnabled(false);
            oneTeam1.setEnabled(false);
            outTeam1.setEnabled(false);
            declareTeam1.setEnabled(false);
        }
        else
        {
            sixTeam2.setEnabled(false);
            fourTeam2.setEnabled(false);
            threeTeam2.setEnabled(false);
            twoTeam2.setEnabled(false);
            oneTeam2.setEnabled(false);
            outTeam2.setEnabled(false);
            declareTeam2.setEnabled(false);
        }
    }

    private void displayGameResult()
    {
        int difference;
        if (setTeam1Disabled == true && setTeam2Disabled == true)
        {
            if (scoreTeam1>scoreTeam2)
            {
                difference = scoreTeam1 - scoreTeam2;
                Toast.makeText(this, "Team 1 won by: "+difference, Toast.LENGTH_LONG).show();
            }
            else if (scoreTeam2>scoreTeam1)
            {
                difference = scoreTeam2 - scoreTeam1;
                Toast.makeText(this, "Team 2 won by: "+difference, Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "It's a draw.", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "Both teams need to declare", Toast.LENGTH_LONG).show();
        }
    }
}
