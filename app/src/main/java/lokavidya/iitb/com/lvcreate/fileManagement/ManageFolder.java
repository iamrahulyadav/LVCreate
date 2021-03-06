package lokavidya.iitb.com.lvcreate.fileManagement;

import android.content.Context;
import android.util.Log;

import java.io.File;

import lokavidya.iitb.com.lvcreate.util.Master;

public class ManageFolder {

    public static boolean createFolderStructure(Context context, String folderName) {

        boolean status = true;

        try {
            String directoryPath = context.getExternalFilesDir(Master.ALL_PROJECTS_FOLDER).getAbsolutePath();
            Log.d("AAD", "Project folder : " + directoryPath);
            String zipDirectoryPath = context.getExternalFilesDir(Master.ALL_PROJECTS_FOLDER).getAbsolutePath();

            File dir;

            dir = new File(zipDirectoryPath);
            status &= createFolder(dir);

            dir = new File(directoryPath, folderName);
            status &= createFolder(dir);

            String projectFolder = directoryPath + "/" + folderName;

            dir = new File(projectFolder, Master.IMAGES_FOLDER);
            status &= createFolder(dir);

            dir = new File(projectFolder, Master.VIDEOS_FOLDER);
            status &= createFolder(dir);

            dir = new File(projectFolder, Master.AUDIOS_FOLDER);
            status &= createFolder(dir);

        } catch (Exception e) {
            Log.e("AAD", "Error in making folders");
            status &= false;
        }
        return status;
    }


    public static boolean createFolder(File folderDirectory) {
        if (!folderDirectory.exists()) {
            if (!folderDirectory.mkdirs()) {
                Log.e("AAD", "folder creation failed");
                return false;
            } else {
                Log.d("AAD", "created folder " + folderDirectory.getName());
                return true;
            }

        }
        return false;
    }


    //http://www.java2s.com/Tutorial/Java/0180__File/Removeadirectoryandallofitscontents.htm
    public static boolean removeFolder(String folderPath) {

        File directory;

        try {
            directory = new File(folderPath);

            if (!directory.exists())
                return true;
            if (!directory.isDirectory())
                return false;

            String[] list = directory.list();

            // Some JVMs return null for File.list() when the
            // directory is empty.
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    File entry = new File(directory, list[i]);

                    if (entry.isDirectory()) {
                        if (!removeFolder(entry.getPath()))
                            return false;
                    } else {
                        if (!entry.delete())
                            return false;
                    }
                }
            }
            Log.d("AAD", "Deleting folder " + directory.getName());
            return directory.delete();
        } catch (Exception e) {
            Log.e("AAD", "Deletion failed");
            e.printStackTrace();
        }
        return false;
    }
}
