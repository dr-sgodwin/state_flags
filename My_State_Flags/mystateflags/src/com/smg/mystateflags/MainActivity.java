package com.smg.mystateflags;

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
        AU.add(new State(R.string.australia_capital_territory, R.drawable.australian_capital_territory, R.string.australia_captial_territory_url));
        AU.add(new State(R.string.new_south_wales, R.drawable.new_south_wales, R.string.new_south_wales_url));
        AU.add(new State(R.string.norhtern_territory, R.drawable.northern_territory, R.string.norhtern_territory_url));
        AU.add(new State(R.string.queensland, R.drawable.queensland, R.string.queensland_url));
        AU.add(new State(R.string.south_australia, R.drawable.south_australia, R.string.south_australia_url));
        AU.add(new State(R.string.tasmania, R.drawable.tasmania, R.string.tasmania_url));
        AU.add(new State(R.string.victoria, R.drawable.victoria, R.string.victoria_url));
        AU.add(new State(R.string.western_australia, R.drawable.western_australia, R.string.western_australia_url));
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
            private ImageView flag = null;
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
                if (flag == null) {
                    flag = (ImageView) row.findViewById(R.id.flag);
                }
                return (flag);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
