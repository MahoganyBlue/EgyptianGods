package com.example.egyptiangods;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    static ArrayList<State> AU = new ArrayList<State>();

    static {
        AU.add(new State(R.string.amum, R.drawable.amum, R.string.amum_url));
        AU.add(new State(R.string.anubis, R.drawable.anubis, R.string.anubis_url));
        AU.add(new State(R.string.atum, R.drawable.atum, R.string.atum_url));
        AU.add(new State(R.string.hathor, R.drawable.hathor, R.string.hathor_url));
        AU.add(new State(R.string.isis, R.drawable.isis, R.string.isis_url));
        AU.add(new State(R.string.maat, R.drawable.maat, R.string.maat_url));
        AU.add(new State(R.string.min, R.drawable.min, R.string.min_url));
        AU.add(new State(R.string.nephthys, R.drawable.nephthys, R.string.nephthys_url));
        AU.add(new State(R.string.ra, R.drawable.ra, R.string.ra_url));
        AU.add(new State(R.string.sekhmet, R.drawable.sekhmet, R.string.sekhmet_url));
        
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListAdapter(new StateAdapter());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(AU.get(position).url))));
    }

    static class State {
        int name;
        int flag;
        int url;

        State(int name, int flag, int url) {
            this.name = name;
            this.flag = flag;
            this.url = url;
        }
    }

    class StateAdapter extends ArrayAdapter<State> {
        StateAdapter() {
            super(MainActivity.this, R.layout.row, R.id.name, AU);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            StateWrapper wrapper = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.row, null);
                wrapper = new StateWrapper(convertView);
                convertView.setTag(wrapper);
            } else {
                wrapper = (StateWrapper) convertView.getTag();
            }
            wrapper.populateFrom(getItem(position));
            return (convertView);

        }

        class StateWrapper {
            private TextView name = null;
            private ImageView god = null;
            private View row = null;

            StateWrapper(View row) {
                this.row = row;
            }

            TextView getName() {
                if (name == null) {
                    name = (TextView) row.findViewById(R.id.name);
                }
                return (name);
            }

            ImageView getFlag() {
                if (god == null) {
                    god = (ImageView) row.findViewById(R.id.gods);
                }
                return (god);
            }

            void populateFrom(State location) {
                getName().setText(location.name);
                getFlag().setImageResource(location.flag);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //no inspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}