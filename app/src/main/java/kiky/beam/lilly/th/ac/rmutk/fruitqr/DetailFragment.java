package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private String qrCode;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment detailInstance(String resultQR) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("QRcode", resultQR);
        detailFragment.setArguments(bundle);
        return detailFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        qrCode = getArguments().getString("QRcode");

//        Show Result
        TextView textView = getView().findViewById(R.id.txtResult);
        textView.setText(qrCode);


    }   // Main Method

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}
