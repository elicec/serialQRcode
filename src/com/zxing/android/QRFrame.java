package com.zxing.android;

public class QRFrame {

	public Head head;
	public byte[] data;
	public int checksum;

	public class Head {
		public int identification;
		public int total;
		public int index;
		public int datasize;
		
		public Head() {
			identification=0;
			total=0;
			index=0;
			datasize=0;
		}
		public void setHead(int id,int total,int index,int length){
			this.identification=id;
			this.total=total;
			this.index=index;
			this.datasize=length;
		}
	}
	

	public static QRFrame build(byte[] data,QRFrame.Head head){
		QRFrame frame=new QRFrame();
		frame.head=head;
		frame.data=data;
		return frame;
	}
/**
 * rawbytes formates(hex):aa ab bc cd de e(12bits+8bits(id)+8bits(total)+8bits(index)+8bits(dataseize))
 * @param rawBytes
 * @return
 */
	public static QRFrame parseRawBytes(byte[] rawBytes){
		QRFrame frame=new QRFrame();
		Head head=new QRFrame().new Head();
		byte[] temp,data;
		temp=new byte[rawBytes.length];
		
		for(int i=0;i<rawBytes.length-2;i++){
			temp[i]=(byte)(((rawBytes[i+1]<<4) & 0xf0) | ((rawBytes[i+2]>>4)&0x0f));
		}
		
		int id= temp[0] & 0x0ff;
		int total=temp[1] & 0x0ff;
		int index=temp[2] & 0x0ff;
		int length=temp[3] & 0x0ff;
		data=new byte[length];
		head.setHead(id, total, index, length);
		System.arraycopy(temp, 4, data, 0, length);
		frame=QRFrame.build(data, head);
		return frame;
		
	}

}
