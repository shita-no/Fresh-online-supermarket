package cn.edu.zucc.sxwc.util;

public class DbException extends BaseException {
	public DbException(java.lang.Throwable ex){
		super("���ݿ��������"+ex.getMessage());
	}
}
