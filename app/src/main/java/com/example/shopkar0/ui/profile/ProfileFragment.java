package com.example.shopkar0.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.shopkar0.R;
import com.example.shopkar0.models.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    CircleImageView profileImage;
    EditText name,email,number,address;

    FirebaseAuth auth;
    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;

    Button updateBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        auth=FirebaseAuth.getInstance();

        profileImage=root.findViewById(R.id.profileImage);
        name=root.findViewById(R.id.profileName);
        email=root.findViewById(R.id.profileEmail);
        number=root.findViewById(R.id.profileNumber);
        address=root.findViewById(R.id.profileAddress);
        updateBtn=root.findViewById(R.id.profileUpdateBtn);

        firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel=snapshot.getValue(UserModel.class);
                Glide.with(getContext()).load(userModel.getProfileImage()).into(profileImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });

        return root;
    }

    private void updateUserProfile() {
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData()!=null){
            Uri profileUri=data.getData();
            profileImage.setImageURI(profileUri);

            final StorageReference storageReference=firebaseStorage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());

            storageReference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Profile Uploaded", Toast.LENGTH_SHORT).show();
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                .child("profileImage").setValue(uri.toString());

                        Toast.makeText(getContext(), "Profile Picture Uploaded ", Toast.LENGTH_SHORT).show();
                    }
                });
                }
            });
        }
    }
}