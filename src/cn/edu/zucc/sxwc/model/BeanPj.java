package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanPj {
		public static final String[] PjTitles={"评价编号","类别编号","用户编号","商品编号","评价内容","评价日期","星级","照片"};
		private int pjid;
		private String userid;
		private String goodsid;
		private String pjcontent;
		private Date pjDate;
		private int rank;
		private String photo;
		public int getPjid() {
			return pjid;
		}
		public void setPjid(int pjid) {
			this.pjid = pjid;
		}
		private String lbid;
		public String getCell(int col){
			if(col==0) return String.valueOf(pjid);
		else if(col==1) return String.valueOf(lbid);
		else if(col==2) return String.valueOf(userid);
		else if(col==3) return String.valueOf(goodsid);
		else if(col==4) return String.valueOf(pjcontent);
		else if(col==5) return String.valueOf(pjDate);
		else if(col==6) return String.valueOf(rank);	
		else if(col==7) return String.valueOf(photo);
		else return "";
	}
		public String getLbid() {
			return lbid;
		}
		public void setLbid(String lbid) {
			this.lbid = lbid;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getGoodsid() {
			return goodsid;
		}
		public void setGoodsid(String goodsid) {
			this.goodsid = goodsid;
		}
		public String getPjcontent() {
			return pjcontent;
		}
		public void setPjcontent(String pjcontent) {
			this.pjcontent = pjcontent;
		}
		public Date getPjDate() {
			return pjDate;
		}
		public void setPjDate(Date pjDate) {
			this.pjDate = pjDate;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		
}
