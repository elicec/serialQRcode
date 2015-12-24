package com.zxing.android;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * electronic note contains a list of QRFrames;
 * @author penghong
 *
 */
public class ENote {
	private List<QRFrame> QRList=new ArrayList<QRFrame>();

	public void add(QRFrame frame){
		if(frame==null)
			return;
		if(isNotHave(frame))
		QRList.add(frame);
	}
	public boolean isNotHave(QRFrame frame){
		int size=QRList.size(),i;
		for(i=0;i<size;i++){
			if(QRList.get(i).head.index==frame.head.index)
				break;
		}
		return (i==size);
	}
	public boolean isComplete(){
		int size=QRList.size();
		if(size<=0)
			return false;
		return(size==QRList.get(0).head.total);
	}
	
	public byte[] getBytes(){
		if(QRList==null) return null;
		int size=QRList.size();
		if(size==0)return null;
		sort();
		byte[] first=QRList.get(0).data;
		int totalLength = first.length;
        for (int i=1;i<size;i++) {
            totalLength += QRList.get(i).data.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (int j=1;j<size;j++) {
            System.arraycopy(QRList.get(j).data, 0, result, offset, QRList.get(j).data.length);
            offset += QRList.get(j).data.length;
        }
        return result;
    }
	
	public String toString(){
		if(getBytes()==null)return "";
		return new String(getBytes());
	}
	public String toString(String charset){
		try {
			return new String(getBytes(),charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	public void sort(){
		Collections.sort(QRList,new Comparator<QRFrame>() {

			@Override
			public int compare(QRFrame lhs,
					QRFrame rhs) {
				return (lhs.head.index-rhs.head.index);
			}
		});
	} 
	
	public static String bytesToHexString(byte[] src){   
	    StringBuilder stringBuilder = new StringBuilder("");   
	    if (src == null || src.length <= 0) {   
	        return null;   
	    }   
	    for (int i = 0; i < src.length; i++) {   
	        int v = src[i] & 0xFF;   
	        String hv = Integer.toHexString(v);   
	        if (hv.length() < 2) {   
	            stringBuilder.append(0);   
	        }   
	        stringBuilder.append(hv);   
	    }   
	    return stringBuilder.toString();   
	}   
	
	public static byte[] ConcatArrays(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

}
