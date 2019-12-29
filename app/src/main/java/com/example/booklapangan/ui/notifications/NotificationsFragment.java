package com.example.booklapangan.ui.notifications;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.booklapangan.ChangePassActivity;
import com.example.booklapangan.EditProfileActivity;
import com.example.booklapangan.LoginActivity;
import com.example.booklapangan.MainActivity;
import com.example.booklapangan.Preferences;
import com.example.booklapangan.R;
import com.example.booklapangan.RegisActivity;
import com.example.booklapangan.apihelper.BaseApiService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment implements View.OnClickListener {

//    private NotificationsViewModel notificationsViewModel;
    TextView nama;
    TextView email;
    ImageView foto;
    TextView sapa;
    ImageView edit;

    Button chapass;
    Button logout;
    Preferences sharedPrefManager;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sharedPrefManager = new Preferences(getActivity().getApplicationContext());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        final View myView = inflater.inflate(R.layout.fragment_notifications, container, false);
        chapass = myView.findViewById(R.id.bt_ChaPass);
        logout = myView.findViewById(R.id.bt_Logout);
        edit = myView.findViewById(R.id.to_Edit);
        sapa = myView.findViewById(R.id.sapa);
        nama = myView.findViewById(R.id.name);
        email = myView.findViewById(R.id.email);
        foto = myView.findViewById(R.id.photo);

        logout.setOnClickListener(this);

        edit.setOnClickListener(this);

        chapass.setOnClickListener(this);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            nama.setText(""+personName);
            email.setText(""+personEmail);
            if(personName.contains(" ")){
                personName= personName.substring(0, personName.indexOf(" "));
                sapa.setText("Hi " +personName+ ", here's your profile");
            }
            Glide.with(this).load(personPhoto).into(foto);
        }else{
            String name_user = sharedPrefManager.getSPNama();
            nama.setText(name_user);
            email.setText(sharedPrefManager.getSPEmail());
            if(name_user.contains(" ")){
                name_user= name_user.substring(0, name_user.indexOf(" "));
                sapa.setText("Hi " +name_user+ ", here's your profile");
            }else{
                sapa.setText("Hi " +name_user+ ", here's your profile");
            }
        }

        return myView;
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_Logout:
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(),"SIGN OUT SUKSES",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }
                        });

                sharedPrefManager.saveSPBoolean(Preferences.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getActivity(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

                break;

            case R.id.to_Edit:
                Intent intentedit = new Intent (getActivity(), EditProfileActivity.class);
                startActivity(intentedit);

                break;

            case R.id.bt_ChaPass:
                Intent intentchapass = new Intent (getActivity(), ChangePassActivity.class);
                startActivity(intentchapass);

                default:
                    break;

        }
    }

}