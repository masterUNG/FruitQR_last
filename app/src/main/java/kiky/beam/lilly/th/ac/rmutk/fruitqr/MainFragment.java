package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor //กำหนดค่าเริ่มต้น
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controllor //เป็นการคลิกปุ่ม
        TextView textView = getView().findViewById(R.id.txtRegister); //กด shift+ctrl+enter เติมส่วนที่ขาด //alt+1 กดปิดหน้าโปรเจค
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Replace Fragment
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentMainFragment, new RegisterFragment()).addToBackStack(null).commit();
            }
        });

//        Login Controller
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String user = userEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                if (user.isEmpty() || password.isEmpty()) {
                    myAlertDialog.normalDialog("Have Space", "Please Fill Every Blank");
                } else {

//                    Check Authen
                    try {

                        MyConstant myConstant = new MyConstant();
                        GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
                        getAllDataThread.execute(myConstant.getUrlGetAllData());

                        String jsonString = getAllDataThread.get();
                        Log.d("6janV1", "json ==> " + jsonString);

                        boolean b = true;
                        String truePassword = null, name = null, surname = null;

                        JSONArray jsonArray = new JSONArray(jsonString);
                        for (int i = 0; i < jsonArray.length(); i += 1) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (user.equals(jsonObject.getString("User"))) {
                                b = false;
                                truePassword = jsonObject.getString("Password");
                                name = jsonObject.getString("Name");
                                surname = jsonObject.getString("Surname");
                            }
                        }

                        if (b) {
                            myAlertDialog.normalDialog("User False", "No This User in My Database");
                        } else if (password.equals(truePassword)) {
                            Toast.makeText(getActivity(), "Welcome " + name + " " + surname, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), ServiceActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            myAlertDialog.normalDialog("Password False", "Please Try Again Password False");
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }


                }





            }
        });



    }   //Main Method เมธอดแรกในการทำงาน

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment //สร้างหน้ากาก หรือ textview
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}//Main Class
