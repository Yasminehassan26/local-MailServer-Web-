package EmailClient;
import java.io.File;
import java.io.IOException;

public class Folder {

    private String path;

    public Folder(String userFolderPath){
        this.path = userFolderPath;
    }

    public String getPath() {
        return this.path;
    }

    public void createFolder(){
        File file = new File(this.path);
        try {
            if(!file.exists()) {
                file.mkdir();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void renameFolder(int id,String renameTo){
        File folderToBeRenamed = new File(this.path);
        File renameFolderTo = new File("Accounts\\"+id+"\\"+renameTo);
        if (folderToBeRenamed.renameTo(renameFolderTo)) {
            System.out.println("Directory renamed successfully");
        } else {
            System.out.println("Failed to rename directory");
        }
    }


    public void deleteFolder(){
        File folderToDelete = new File(this.path);
        File[] contents = folderToDelete.listFiles();
        if(contents!=null) {
            for( File file: contents) {
                file.delete();
            }
        }
        folderToDelete.delete();
    }
}
