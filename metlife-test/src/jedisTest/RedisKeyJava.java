package jedisTest;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
public class RedisKeyJava {
   public static void main(String[] args) {
      //连接本地的 Redis 服务
      Jedis jedis = new Jedis("localhost");
      System.out.println("Connection to server sucessfully");
      
     // 获取数据并输出
//     List<String> list = (List<String>) jedis.keys("*");
     Set<String> set =jedis.keys("*");
//     for(int i=0; i<set.size(); i++) {
//       System.out.println("List of stored keys:: "+((Jedis) set).get(i));
//     }
     Iterator it = set.iterator();
     System.out.println(set.size());
     while(it.hasNext()) {  
    	    System.out.println(it.next());  
    	}  
     for(String s:set){
    	 System.out.println(s);
     }
     try {
		System.out.println(	java.net.URLEncoder.encode("你好","UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}