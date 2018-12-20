 package com.example.android.soccerscoretracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {

    int homeScore, awayScore = 0;
    int homeYellow, awayYellow = 0;
    int homeRed, awayRed = 0;
    int homeSubs, awaySubs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initialise (View v) {
        display((TextView)findViewById(R.id.home_team_score),homeScore = 0);
        display((TextView)findViewById(R.id.away_team_score),awayScore = 0);
        display((TextView)findViewById(R.id.home_team_subs),homeSubs = 0);
        display((TextView)findViewById(R.id.away_team_subs),awaySubs = 0);
        display((TextView)findViewById(R.id.home_team_yellow),homeYellow = 0);
        display((TextView)findViewById(R.id.away_team_yellow),awayYellow = 0);
        display((TextView)findViewById(R.id.home_team_red),homeRed = 0);
        display((TextView)findViewById(R.id.away_team_red),awayRed = 0);

    }

    /* Displays the values*/
    public void display(TextView v, int value) {
        v.setText(Integer.toString(value));
    }

    //Tallys the goal scored by the team
    public void goal(View v) {
        switch(v.getId()) {
            case (R.id.button_home_team_score):
                display((TextView)findViewById(R.id.home_team_score), ++homeScore);
                break;
            case(R.id.button_away_team_score):
                display((TextView)findViewById(R.id.away_team_score), ++awayScore);
                break;
        }
    }

    //Tracks the number of substitutions made by the team.
     public void substitution (View v) {
         switch(v.getId()) {
             case (R.id.button_home_team_subs):
                 if (homeSubs < 3)
                     display((TextView)findViewById(R.id.home_team_subs), ++homeSubs);
                 else
                     Toast.makeText(getApplicationContext(),"No more substitutions available for Home Team.",Toast.LENGTH_LONG).show();
                 break;
             case(R.id.button_away_team_subs):
                 if (awaySubs < 3)
                    display((TextView)findViewById(R.id.away_team_subs), ++awaySubs);
                 else
                     Toast.makeText(getApplicationContext(),"No more substitutions available for Away Team.",Toast.LENGTH_LONG).show();
                 break;
         }
     }

     //Tracks the number of fouls by the team, segregated on the basis of red and yellow cards.
     public void foul (View v) {
        switch(v.getId()) {
            case (R.id.button_home_team_yellow):
                if (foulCheck(homeYellow,homeRed))
                    display((TextView)findViewById(R.id.home_team_yellow), ++homeYellow);
                break;
            case(R.id.button_away_team_yellow):
                if (foulCheck(awayYellow,awayRed))
                    display((TextView)findViewById(R.id.away_team_yellow), ++awayYellow);
                break;
            case (R.id.button_home_team_red):
                if (foulCheck(homeYellow,homeRed))
                    display((TextView)findViewById(R.id.home_team_red), ++homeRed);
                break;
            case(R.id.button_away_team_red):
                if (foulCheck(awayYellow,awayRed))
                    display((TextView)findViewById(R.id.away_team_red), ++awayRed);
                break;
        }
     }

     //Checks if the entire team or more than half of the team has been sent of the play.
     public boolean foulCheck(int yellow, int red) {
        if (yellow < 24 && red < 6)
            return true;
        else{
            Toast.makeText(getApplicationContext(),"Majority or complete team is sent off, opposition wins.", Toast.LENGTH_LONG).show();
            initialise(null);
            return false;
        }

     }
}
