package cn.edu.zucc.sxwc.model;

public class BeanLb {
		public static final String[] tableTitles={"类别编号","类别名称","类别描述"};
		private String lbid;
		private String lbname;
		private String described;//类别描述
		public String getCell(int col){
			if(col==0) return String.valueOf(lbid);
			else if(col==1) return String.valueOf(lbname);
			else if(col==2) return String.valueOf(described);
			else return "";
		}
		public String getLbid() {
			return lbid;
		}
		public void setLbid(String lbid) {
			this.lbid = lbid;
		}
		public String getLbname() {
			return lbname;
		}
		public void setLbname(String lbname) {
			this.lbname = lbname;
		}
		public String getDescribed() {
			return described;
		}
		public void setDescribed(String described) {
			this.described = described;
		}
		
}
