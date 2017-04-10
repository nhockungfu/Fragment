package org.o7planning.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by USER on 4/1/2017.
 */

public class FragmentBlue extends Fragment implements FragmentBlueCallBack{
    MainActivity main;
    Context context = null;
    String message = "";
    int select = 0;
    ListView listView;
    TextView txtBlue;
    ArrayAdapter<String> adapter;

    private String items[] = {"Text-on-Line-00", "Text-on-Line-01",
            "Text-on-Line-02", "Text-on-Line-03", "Text-on-Line-04",
            "Text-on-Line-05", "Text-on-Line-06", "Text-on-Line-07",
            "Text-on-Line-08", "Text-on-Line-09", "Text-on-Line-10"};

    public static FragmentBlue newInstance(String strArg) {
        FragmentBlue fragment = new FragmentBlue();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            // use this reference to invoke main callbacks
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException(
                    "MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate res/layout_blue.xml to make GUI holding a TextView and a ListView
        LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.layout_blue, null);
        // plumbing – get a reference to textview and listview
        txtBlue = (TextView) layout_blue.findViewById(R.id.textView1Blue);
        listView = (ListView) layout_blue.findViewById(R.id.listView1Blue);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // define a simple adapter to fill rows of the listview
        adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, items)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =  super.getView(position, convertView, parent);
                if(select == position)
                    view.setBackgroundColor(Color.DKGRAY);
                else
                    view.setBackgroundColor(Color.parseColor("#ffccddff"));
                return view;
            }
        };
        listView.setAdapter(adapter);
        // show listview from the top
        listView.setSelected(true);
        listView.setItemsCanFocus(true);
        // react to click events on listview’s rows
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                main.onMsgFromFragToMain("BLUE-FRAG", "Blue selected row=" + position);
                txtBlue.setText("Blue selected row=" + position);
                select = position;
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // inform enclosing MainActivity of the row’s position just selected
                main.onMsgFromFragToMain("BLUE-FRAG", "Blue selected row=" + position);
                txtBlue.setText("Blue selected row=" + position);
                select = position;
                adapter.notifyDataSetChanged();
            }
        });

        // do this for each row (ViewHolder-Pattern could be used for better performance!)
        return layout_blue;
    }// onCreateView


    @Override
    public void onMsgFromMainToFragmentBlue(String key) {
        switch (key)
        {
            case "0":
            {
                select = 0;
                listView.setSelection(0);
                adapter.notifyDataSetChanged();
                main.onMsgFromFragToMain("BLUE-FRAG", "Blue selected row=" + select);
                txtBlue.setText("Blue selected row=" + select);
                break;
            }
            case "1":
            {
                select++;
                if(select == items.length)
                    select--;
                listView.setSelection(select);
                adapter.notifyDataSetChanged();
                main.onMsgFromFragToMain("BLUE-FRAG", "Blue selected row=" + select);
                txtBlue.setText("Blue selected row=" + select);

                break;

            }
            case "2":
            {
                select--;
                if(select == -1)
                    select = 0;
                listView.setSelection(select);
                adapter.notifyDataSetChanged();
                main.onMsgFromFragToMain("BLUE-FRAG", "Blue selected row=" + select);
                txtBlue.setText("Blue selected row=" + select);

                break;
            }
            case "3":
            {
                select = items.length - 1;
                listView.setSelection(select);
                adapter.notifyDataSetChanged();
                main.onMsgFromFragToMain("BLUE-FRAG", "Blue selected row=" + select);
                txtBlue.setText("Blue selected row=" + select);

                break;
            }

        }
    }
}

