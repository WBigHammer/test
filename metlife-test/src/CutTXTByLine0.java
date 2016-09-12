import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CutTXTByLine0 {

	public static void main(String[] args) {
		System.out.println("begin");
		Long startTime = System.currentTimeMillis();
		String file2Cut="E:/cutByLine/source/f_la_acmvpf.txt";//���ָ��ļ�
		String devFile="E:/cutByLine/devisionFiles";//�ָ���ļ����λ��
		CutTXT(file2Cut,devFile);
		Long endTime = System.currentTimeMillis();
		Long time = endTime - startTime;
		System.out.println("�ܺ�ʱ��" + time + "����");
		System.out.println("end");
	}

	/**
	 * 
	 * Description: TXT�ļ��и�
	 * 
	 * @param fileName
	 *            ���и��ļ���ַ
	 * @param filesPath
	 *            �и���ļ��е�ַ
	 * 
	 */
	public static void CutTXT(String fileName, String filesPath) {
		// fileName = "D:/20.txt";
		BufferedReader reader;
		int columns=63;//||����
		int lines=10;//ÿһ�����ļ��д������
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
				String s = "";
				Long lineNumber = 1L;
				Long fileIndex=1L;
				File f = new File(filesPath);
				if (!f.exists() && !f.isDirectory()) {
					f.mkdirs();
				} else {
					clearFiles(filesPath);
					f.mkdirs();
				}
				String subFileName=System.currentTimeMillis()+"_"+fileIndex+".txt";
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filesPath+"/"+subFileName)));
				System.out.println("���ļ���"+subFileName);
				s = reader.readLine();
				while (s!= null) {
					if(s.trim().split("\\|\\@\\|").length==columns&&s.contains("|@| HUAN HANG")){
						writer.append(s.replaceAll("\\\\r\\\\n", "").replaceAll("(\\|\\@\\| HUAN HANG)", "\r\n"));
						s=reader.readLine();
					}else{
						writer.append(s.replaceAll("\\\\r\\\\n", "").replaceAll("(\\|\\@\\| HUAN HANG)", "\r\n"));
						s=reader.readLine();
						while(s!= null&&s.trim().split("\\|\\@\\|").length!=columns){
							if(s.contains("|@| HUAN HANG")){
								writer.append(s.replaceAll("\\\\r\\\\n", "").replaceAll("(\\|\\@\\| HUAN HANG)", "\r\n"));
								s=reader.readLine();
								lineNumber++;
								if(lineNumber%lines==1){
									writer.close();
									fileIndex++;
									subFileName=System.currentTimeMillis()+"_"+fileIndex+".txt";
									writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filesPath+"/"+subFileName)));
									System.out.println("���ļ���"+subFileName);
								}
								writer.flush();
								continue;
							}else{
								writer.append(s.replaceAll("\\\\r\\\\n", ""));
								s=reader.readLine();
							}
						}
					}
					lineNumber++;
					if(lineNumber%lines==1){
						writer.close();
						fileIndex++;
						subFileName=System.currentTimeMillis()+"_"+fileIndex+".txt";
						writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filesPath+"/"+subFileName)));
						System.out.println("���ļ���"+subFileName);
					}
					writer.flush();
				}
				writer.close();
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