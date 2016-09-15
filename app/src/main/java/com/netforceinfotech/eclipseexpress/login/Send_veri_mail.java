package com.netforceinfotech.eclipseexpress.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.netforceinfotech.eclipseexpress.R;


public class Send_veri_mail extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
Verify_done veri_mail;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public Send_veri_mail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Send_veri_mail.
     */
    // TODO: Rename and change types and number of parameters
    public static Send_veri_mail newInstance(String param1, String param2) {
        Send_veri_mail fragment = new Send_veri_mail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_send_veri_mail, container, false);
        Initview(v);
        // Inflate the layout for this fragment
        return v;
    }

    private void Initview(View v) {
        Button send_verify=(Button)v.findViewById(R.id.snd_to_verify);
        send_verify.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.snd_to_verify:
                setupforgotFragment();
                break;


        }
    }

    // TODO: Rename method, update argument and hook method into UI event

    private void replaceFragment(Fragment newFragment, String tag) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_forgot, newFragment, tag);
        transaction.commit();
    }

    private void setupforgotFragment() {
        if (veri_mail == null) {
            veri_mail = new Verify_done();
        }
        String tagName = veri_mail.getClass().getName();
        replaceFragment(veri_mail, tagName);
    }





}
