package cn.edu.zucc.sxwc.model;

import java.sql.Date;

public class BeanPj {
		private String userid;
		private String goodsid;
		private String pjcontent;
		private Date pjDate;
		private int rank;
		private String photo;
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
