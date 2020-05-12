package ru.mirea.casehistory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditRecordActivity extends AppCompatActivity {
    //    private ImageView pImageView;
    private EditText pNameEt, pIllnesesEt;
    Button saveInfoBt;
    ActionBar actionBar;

//    private static final int CAMERA_REQUEST_CODE = 100;
//    private static final int STORAGE_REQUEST_CODE = 101;
//
//    private static final int IMAGE_PICK_CAMERA_CODE = 102;
//    private static final int IMAGE_PICK_GALLERY_CODE = 103;

//    private String[] cameraPermissions;
//    private String[] storagePermissions;

//    private Uri imageUri;

    private String id, name, illnesses, addTimeStamp, updateTimeStamp;
    private boolean editMode = false;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        pImageView = findViewById(R.id.personImage);

        pNameEt = findViewById(R.id.personName);
        pIllnesesEt = findViewById(R.id.personIllness);

        saveInfoBt = findViewById(R.id.addButton);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode", editMode);
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        illnesses = intent.getStringExtra("ILLNESSES");
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        if(editMode){

            actionBar.setTitle("Обновите информацию");

            editMode = intent.getBooleanExtra("editMode", editMode);
            id = intent.getStringExtra("ID");
            name = intent.getStringExtra("NAME");
            illnesses = intent.getStringExtra("ILLNESSES");
            addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
            updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

            pNameEt.setText(name);
            pIllnesesEt.setText(illnesses);
        }
        else{

            actionBar.setTitle("Добавьте информацию");
        }


//        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        dbHelper = new DatabaseHelper(this);

//        pImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imagePickDialog();
//            }
//        });

        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                startActivity(new Intent(EditRecordActivity.this,  MainActivity.class));
                Toast.makeText(EditRecordActivity.this, "Данные обновлены", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setData() {

        name = ""+pNameEt.getText().toString().trim();
        illnesses = ""+pIllnesesEt.getText().toString().trim();

        if (editMode){

            String newUpdateTime = ""+System.currentTimeMillis();

            dbHelper.updateInfo(
                    ""+id,
                    ""+name,
                    ""+illnesses,
                    ""+addTimeStamp,
                    ""+newUpdateTime
            );
        }
        else{
            String timeStamp = ""+System.currentTimeMillis();
            long id = dbHelper.insertInfo(
                    ""+name,
                    ""+illnesses,
                    ""+timeStamp,
                    ""+timeStamp
            );
        }
    }

//    private void imagePickDialog() {
//
//        String[] options = {"Камера", "Галерея"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder( this);
//
//        builder.setTitle("Выберите изображение");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                if(which == 0){
//
//                    if(!checkCameraPermission()){
//                        requestCameraPermission();
//                    }
//                    else{
//                        pickFromCamera();
//                    }
//                }
//                else if (which == 1){
//
//                    if(!checkStoragePermission()){
//                        requestStoragePermission();
//                    }
//                    else{
//                        pickFromStorage();
//                    }
//                }
//            }
//        });
//        builder.create().show();
//    }
//
//    private void pickFromStorage() {
//
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
//        galleryIntent.setType("image/*");
//        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
//    }
//
//    private void pickFromCamera() {
//
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, "Image title");
//        values.put(MediaStore.Images.Media.DESCRIPTION, "Image description");
//
//        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
//
//    }
//
//    private boolean checkStoragePermission(){
//
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == (PackageManager.PERMISSION_GRANTED);
//
//        return result;
//    }
//
//    private void requestStoragePermission(){
//
//        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
//    }
//
//    private boolean checkCameraPermission(){
//
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                == (PackageManager.PERMISSION_GRANTED);
//
//        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == (PackageManager.PERMISSION_GRANTED);
//
//        return result  && result1;
//    }
//
//    private void requestCameraPermission(){
//
//        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode){
//
//            case CAMERA_REQUEST_CODE:{
//
//                if (grantResults.length>0){
//
//                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//
//                    if(cameraAccepted && storageAccepted){
//                        pickFromCamera();
//                    }
//                    else{
//                        Toast.makeText(this, "Требуется разрешение на использование камеры!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//            break;
//
//            case STORAGE_REQUEST_CODE:{
//                if (grantResults.length>0){
//
//                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//
//                    if(storageAccepted){
//                        pickFromStorage();
//                    }
//                    else{
//                        Toast.makeText(this, "Требуется разрешение на использование галереи!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if(requestCode == RESULT_OK){
//            if(requestCode == IMAGE_PICK_GALLERY_CODE){
//
//                CropImage.activity(data.getData())
//                        .setGuidelines(CropImageView.Guidelines.ON)
////                        .setAspectRatio(1,1)
//                        .start(this);
//            }
//            else if(requestCode == IMAGE_PICK_CAMERA_CODE){
//
//                CropImage.activity(imageUri)
//                        .setGuidelines(CropImageView.Guidelines.ON)
////                        .setAspectRatio(1,1)
//                        .start(this);
//            }
//            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
//
//                CropImage.ActivityResult result = CropImage.getActivityResult(data);
//
//                if(resultCode == RESULT_OK){
//
//                    Uri resultUri = result.getUri();
//                    imageUri = resultUri;
//                    pImageView.setImageURI(resultUri);
//                }
//                else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
//
//                    Exception error = result.getError();
//                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
