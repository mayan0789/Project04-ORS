package in.co.rays.util;

import java.util.Date;

/**
 * @author Mayank
 *
 */
public class DataValidator {

	  /**
     * Checks if value is Null
     * 
     * 
     */
    public static boolean isNull(String val) {
        if (val == null || val.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if value is NOT Null
     * 
     *
     */
    public static boolean isNotNull(String val) {
        return !isNull(val);
    }

    /**
     * Checks if value is an Integer
     * 
     
     */

    public static boolean isInteger(String val) {

        if (isNotNull(val)) {
     //   	System.out.println("datavalidate..DataValidator$"+val+"$");
            try {
            	
                int i = Integer.parseInt(val);
       //         System.out.println("data validate ......"+i);
                return true;
            } catch (NumberFormatException e) {
            	e.printStackTrace();
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is Long
     * 
    
     */
    public static boolean isLong(String val) {
        if (isNotNull(val)) {
            try {
                long i = Long.parseLong(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is valid Email ID
     * 
     */
    public static boolean isEmail(String val) {

        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (isNotNull(val)) {
            try {
                return val.matches(emailreg);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is Date
     * 
     */
    public static boolean isDate(String val) {

        Date d = null;
        if (isNotNull(val)) {
            d = DataUtility.getDate(val);
        }
        return d != null;
    }

    /**
     * Checks if value is Mobile Number
     * 
     */
 
    public static boolean isMobileNo(String val){
    	
    	String mobreg = "^[6-9][0-9]{9}$";
    	
    			if (isNotNull(val) && val.matches(mobreg)) {
					
						return true;
    				}else
    				{	
    					return false;
					}	
    		}

    
    
    /**
     * checks if value is Name
     */
    public static boolean isName(String val){
    	
    //	String namereg = "^[a-zA-Z]+$";
   // 	String namereg = "^[a-zA-Z_-]+$";
   	String namereg = "^[^-\\s][\\p{L} .']+$";
    	
    //	     System.out.println(val);
    			if (DataValidator.isNotNull(val)) {
    				boolean check = val.matches(namereg);
    	//			System.out.println(check);
						return check;
    				}else
    				{	
    					return false;
					}	
    		}
   
    /**
     * check if value is Valid Name
     */
    public static boolean isValidName(String val){
    	
        //	String namereg = "^[a-zA-Z]+$";
        	
        	String namereg = "^[a-zA-Z_-]+$";
       //	String namereg = "^[^-\\s][\\p{L} .']+$";
        	
        	//     System.out.println(val);
        			if (DataValidator.isNotNull(val)) {
        				boolean check = val.matches(namereg);
        	//			System.out.println(check);
    						return check;
        				}else
        				{	
        					return false;
    					}	
        		}
    
    
    /**
     * check if value is password
     */
    public static boolean isPassword(String val){
    	
    	String pass = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
    	
    			if (DataValidator.isNotNull(pass)) {
					boolean check =  val.matches(pass);
						return check;
    				}else
    				{	
    					return false;
					}	
    		}
    
    /**
     * check if value is Roll no
     */
    public static boolean isRollNo(String val){
    	
    	String roll = "^[0-9]{2}[A-Z]{2}[0-9]{2,6}$";
    
    			if (DataValidator.isNotNull(roll)) {
					boolean check = val.matches(roll);
		//			System.out.println("rolllllllllll"+check);
						return check;
    				}else
    				{	
    					return false;
					}	
    		}
    
    /**
     * check if value is validate age
     */
    public static boolean isvalidateAge(String val){
    
    	Date today = new Date();
    	Date enterDate = DataUtility.getDate(val);
    	
    	int age = today.getYear() - enterDate.getYear();

    	if(age > 18 && isNotNull(val)){
    		return true;
    	}else{
    		return false;						
    		
    	}
    }
    public static boolean isAdd(String val) {

        String emailreg = "^[_A-Za-z0-9-]$";

        if (isNotNull(val)) {
            try {
                return val.matches(emailreg);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }
    
    /**
     * Test above methods
     * 
     * @param args
     */
    public static void main(String[] args) {

        /*System.out.println("Not Null 2" + isNotNull("ABC"));
        System.out.println("Not Null 3" + isNotNull(null));*/
       // System.out.println("Not Name" + isName("pankaj"));
      //  String s = "a hajk     a mdlvkj   lkjlkgkl.    /lkjlkhklvjlk";
        String a ="a     di    ty       a";
       
        String c = "amav";
        System.out.println(isValidName(c));
        System.out.println(isName(a));
        
        String b = "Amitjhb54@"; 
        System.out.println(isPassword(b));
        String g = "25/09/2000";
        System.out.println(isvalidateAge(g));
        
        /*System.out.println("Is Int " + isInteger(null));
        System.out.println("Is Int " + isInteger("ABC1"));
        System.out.println("Is Int " + isInteger("123"));
        System.out.println("Is Int " + isNotNull("123"));
    }*/
    }

}
