import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

public class CombinationFile {

	public static void main(String[] args) {
		System.out.println("begin�ϲ��ļ�");
		Long startTime = System.currentTimeMillis();
		String toComDir="E:/cutByLine/devisionFiles/"+args[0];//�ָ���ļ����λ��
		String comDir="E:/cutByLine/combinationFile";//�ϲ����ļ�λ��
		String fileName=args[0];//�ϲ�����ļ�����
		FileCombination(toComDir,comDir,fileName);
		Long endTime = System.currentTimeMillis();
		Long time = endTime - startTime;
		System.out.println("�ܺ�ʱ��" + time + "����");
		System.out.println("�ļ��ϲ�end");
	}

	/**
	 * 
	 * Description: �ļ��ϲ�
	 * 
	 * @param toComDir
	 *            ���ϲ��ļ��е�ַ
	 * @param comDir
	 *            �ϲ����ļ��е�ַ
	 * @param fileName
	 *            �ϲ����ļ���
	 * 
	 */
	public static void FileCombination(String toComDir, String comDir,String fileName) {

		try {
			File[] array = new File(toComDir).listFiles();
			File f = new File(comDir);
			if (!f.exists() && !f.isDirectory()) {
				f.mkdirs();
			}
			/*else {
				clearFiles(comDir);
				f.mkdirs();
			}*/
			RandomAccessFile hFileWrite = new RandomAccessFile(comDir
					+ "/"+fileName, "rw");
			for (int i = 0; i < array.length; i++) {
				if (array[i].isFile()) {
					System.out.println(array[i].getName());
					DataInputStream in = new DataInputStream(
							new FileInputStream(array[i]));
					int fileSize = in.available();
					byte[] buffer = new byte[fileSize];
					in.read(buffer);
					in.close();
					hFileWrite.write(buffer);
				}
			}
			hFileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			clearFiles(toComDir);
		}

	}
	public static void FileCombination_(String toComDir, String comDir,String fileName) throws Exception {
		
		try {
			File[] array = new File(toComDir).listFiles();
			File f = new File(comDir);
	        byte buffer[]=new byte[1024];  

			if (!f.exists() && !f.isDirectory()) {
				f.mkdirs();
			} else {
				clearFiles(comDir);
				f.mkdirs();
			}
			RandomAccessFile hFileWrite = new RandomAccessFile(comDir
					+ "/"+fileName, "rw");
			for (File file:array) {
				if (file.isFile()) {
					System.out.println(file.getName());
					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					while(in.read(buffer)!=-1){
						
					}
					in.close();
					hFileWrite.write(buffer);
				}
			}
			hFileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// ɾ���ļ���Ŀ¼
	private static void clearFiles(String filepath) {
		File file = new File(filepath);
		if (file.exists()) {
			deleteFile(file);
		}
	}

	private static void deleteFile(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
		}
		file.delete();
	}

}