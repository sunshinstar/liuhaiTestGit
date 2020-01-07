package testdemo.emptyNumber.utils;


import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author JiangPengFei
 * @version $Id: mayun-new, v 0.1 2018/12/27 11:06 JiangPengFei Exp $$
 */
public class FileToBase64 {

    /**
     * 读取文件编码
     * @param path
     * @return
     * @throws Exception
     */
    public static String getCharset(String path) throws Exception{
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        boolean checked = false;
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        bis.mark(0);
        int read = bis.read(first3Bytes, 0, 3);
        if (read == -1) {
        } else if (first3Bytes[0] == (byte) 0xFF
                   && first3Bytes[1] == (byte) 0xFE) {
            //文件编码为 Unicode
            charset = "UTF-16LE";
            checked = true;
        } else if (first3Bytes[0] == (byte) 0xFE
                   && first3Bytes[1] == (byte) 0xFF) {
            //文件编码为 Unicode big endian
            charset = "UTF-16BE";
            checked = true;
        } else if (first3Bytes[0] == (byte) 0xEF
                   && first3Bytes[1] == (byte) 0xBB
                   && first3Bytes[2] == (byte) 0xBF) {
            //文件编码为 UTF-8
            charset = "UTF-8";
            checked = true;
        }
        bis.reset();
        if (!checked) {
            int loc = 0;
            while ((read = bis.read()) != -1) {
                loc++;
                if (read >= 0xF0){
                    break;
                }
                // 单独出现BF以下的，也算是GBK
                if (0x80 <= read && read <= 0xBF) {
                    break;
                }
                if (0xC0 <= read && read <= 0xDF) {
                    read = bis.read();
                    // 双字节 (0xC0 - 0xDF)
                    if (0x80 <= read && read <= 0xBF) {
                        // (0x80
                        // - 0xBF),也可能在GB编码内
                        continue;
                    }else {
                        break;
                    }
                    // 也有可能出错，但是几率较小
                } else if (0xE0 <= read && read <= 0xEF) {
                    read = bis.read();
                    if (0x80 <= read && read <= 0xBF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            charset = "UTF-8";
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        bis.close();
        return  charset;
    }

    /**
     * <p>将base64字符解码保存文件</p>
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code, String targetPath,String charset) throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);

        // 指定路径如果没有则创建并添加
        File file = new File(targetPath);
        //获取父目录
        File fileParent = file.getParentFile();
        //判断是否存在
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(targetPath);
        if (StringUtils.isNotBlank(charset)) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, charset));
            bw.write(new String(buffer, Charset.forName(charset)));
            bw.close();
            out.close();
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
            bw.write(new String(buffer, Charset.forName("UTF-8")));
            bw.close();
            out.close();
        }
    }

	/**
	 * <p>将号码保存文件</p>
	 *
	 * @param phones
	 * @param targetPath
	 * @throws Exception
	 */
	public static void savePhoneFile(String phones, String targetPath,String charset) throws Exception {

		// 指定路径如果没有则创建并添加
		File file = new File(targetPath);
		//获取父目录
		File fileParent = file.getParentFile();
		//判断是否存在
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(targetPath);
		if (StringUtils.isNotBlank(charset)) {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, charset));
			bw.write(phones);
			bw.close();
			out.close();
		} else {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
			bw.write(phones);
			bw.close();
			out.close();
		}
	}

    /**
     * <p>将base64字符保存文本文件</p>
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code, String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 读出文件所有内容
     *
     * @param fileName
     * @return
     */
    public static String readToString(String fileName,String charset) {
        File file = new File(fileName);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(fileContent, charset);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + charset);
            e.printStackTrace();
            return null;
        }
    }
}
