package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QRFragment extends Fragment implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private String resultQRcode;


    public QRFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        zXingScannerView = new ZXingScannerView(getActivity());
        return zXingScannerView;
    }

    @Override
    public void handleResult(Result result) {

        resultQRcode = result.toString().trim();
        Log.d("6janV2", "result ==> " + resultQRcode);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentQRFragment, DetailFragment.detailInstance(resultQRcode)).commit();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                zXingScannerView.resumeCameraPreview(QRFragment.this);
            }
        }, 2000);

    }
}
