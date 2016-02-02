package udacity_project.passjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PassJokeActivityFragment extends Fragment {

    public PassJokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pass_joke, container, false);
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(PassJokeActivity.JOKE_KEY);
        TextView jokeText = (TextView) root.findViewById(R.id.joke_textview);
        if (joke != null && joke.length() != 0) {
            jokeText.setText(joke);
        }

        return root;
    }
}
