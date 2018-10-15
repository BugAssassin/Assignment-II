
    import java.util.*;
    import java.util.regex.Pattern;

    //contains Product info stored in Arraylist
    public class ProductInfo{

        String catID;
        String prodID;
        String pName;
        String des;
        String price;

        public ProductInfo(String catID,String prodID,String pName,String des,String price) {
            this.catID = catID;
            this.prodID=prodID;
            this.pName=pName;
            this.des=des;
            this.price=price;

        }
    }

    //contains Category info also stored in ArrayList (it also has some default categories)
    class CategoryInfo
    {
        String catID;
        String cname;
        public CategoryInfo(String catID,String cname)
        {
            this.catID=catID;
            this.cname=cname;
        }
    }

    //This class consists of all the modules
     class DisMenu extends Exception{

        Scanner in = new Scanner(System.in);
        ArrayList<ProductInfo> pl = new ArrayList<>();
        ArrayList<CategoryInfo>cl= new ArrayList<>();
        Pattern p;
        //default categories
        public DisMenu()
        {
        CategoryInfo ci1=new CategoryInfo("ABCD1","Home Appliances");
        CategoryInfo ci2=new CategoryInfo("ABCD2","Cosmetics");
        CategoryInfo ci3=new CategoryInfo("ABCD3","Grocery");
        cl.add(ci1);
        cl.add(ci2);
        cl.add(ci3);
        }
        public boolean checkDuplicate(String prod)
        {
            int i,count=0;
            for(i=0;i<pl.size();i++)
            {
                if(prod.equals(pl.get(i).prodID))
                {
                    count++;
                }

            }
            if(count==0)
                return false;
            else
                return true;
        }
        public String getProdID()
        {   
            String prod;
            int count=0;
            prod=in.next();
            if(pl.size()==0)
                return prod;
            else
            {

            while((Pattern.matches("[[0-9]+[a-zA-Z]|[a-zA-Z][0-9]+]{5}",prod)!=true)|| checkDuplicate(prod))
            {

                    System.out.println("Please enter a valid ProdID"); 
                    System.out.println("The ID that you entered is either invalid or already exists");
                    prod=getProdID();
            }

            return prod;
            }

        }
        public boolean checkDuplicateCat(String cat)
        {
          int i,count=0;
            for(i=0;i<cl.size();i++)
            {
                if(cat.equals(cl.get(i).catID))
                {
                    count++;
                }

            }
            if(count==0)
                return false;
            else
                return true;  
        }
        public String getCatID()
        {
            String cat;
            int count=0;
            cat=in.next();
            if(cl.size()==0)
                return cat;
            else
            {

            while((Pattern.matches("[[0-9]+[a-zA-Z]|[a-zA-Z][0-9]+]{5}",cat)!=true)|| checkDuplicateCat(cat))
            {

                    System.out.println("Please enter a valid ProdID"); 
                    System.out.println("The ID that you entered is either invalid or already exists");
                    cat=getCatID();
            }

            return cat;
            }
        }

        //Method to add a Product
        public void addProduct()
        {
            String cat,prod,pname,des,price;
            int count=0;
            System.out.println("Enter Category ID    :");
            System.out.println("Select from the given Categories:");
            for(int i=0;i<cl.size();i++)
            {
                System.out.println("Category ID: "+cl.get(i).catID+"  Category Name: "+cl.get(i).cname);
            }
            cat=in.next();


            for(int i=0;i<cl.size();i++)
            {
                if(cl.get(i).catID.equals(cat))
                {
                    count++;
                    break;
                }

            }
            if(count==0)
                addProduct();
            else
            {

              //regular expression for validation-must have exactly five characters and atleast one number
            System.out.println("Enter Product ID     :");
            prod=getProdID();


            //must be minimum 10 characters long
            System.out.println("Enter Product Name   :");
            pname=in.next();
            while(Pattern.matches("[a-zA-Z]{10,}", pname)!=true)
            {
                System.out.println("Enter a valid Product name(it should contain minimum 10 characters)");
                pname=in.next();
            }
            System.out.println("Enter Description    :");
            des=in.next();
            //description having minimum 50 characters
            while(Pattern.matches("[a-zA-Z ]{10,}",des)!=true)
            {
                System.out.println("Description should be minimum 50 characters long");
                des=in.next();
            }
            //price range between 500-25000
            System.out.println("Enter Price of a Unit:");
            try
            {
            price=in.next();
            while((Integer.parseInt(price) < 500 )|| (Integer.parseInt(price)>25000))
            {
                throw new DisMenu();
            }

            }
            catch(DisMenu my)
            {
                System.out.println("Price should be in the range 500-25000");
                price=in.next();
            }

            ProductInfo pi=new ProductInfo(cat,prod,pname,des,price);
            pl.add(pi);
            menu();
            }

        }
        //Method to add Category
        public void addProductCat()
        {
            String catID,cname;
            System.out.println("Enter Category ID    :");

            catID=getCatID();
            System.out.println("Enter Category Name  :(Should be a string consisting of maximum 7 characters)");
            cname=in.next();
            while(Pattern.matches("[a-zA-Z]{1,7}",cname)!=true)
            {
                System.out.println("Please Enter a valid name(Should be string consisting of maximum 7 characters)");
                cname=in.next();
            }
            CategoryInfo ci=new CategoryInfo(catID,cname);
            cl.add(ci);
            menu();

        }
        //Method to display Categories
         public void displayCat()
        {
            System.out.println("We have the following categories");
         /* System.out.println("Category ID: ABCD1  Category Name:Home Appliances");
            System.out.println("Category ID: ABCD2  Category Name:Cosmetics");
            System.out.println("Category ID: ABCD3  Category Name:Grocery");*/
            if(cl.size()==0)
                System.out.println("");
            else
            {
                for(int i=0;i<cl.size();i++)
                {
                    System.out.println("Category ID: "+cl.get(i).catID+" Category Name:"+cl.get(i).cname);

                }
            }

            menu();
        }
         //Method to display products
        public void displayProd()
        {
            if(pl.size()==0)
                 System.out.println("No products to display");
            else
            {
                for (int i = 0; i < pl.size(); i++) 
                 {
                   System.out.print("Category ID         : " + pl.get(i).catID + ",  ");
                   System.out.print("Product  ID         : " + pl.get(i).prodID+ ",   ");
                   System.out.print("Product  Name       : " + pl.get(i).pName+ ",   ");
                   System.out.print("Product Description : " + pl.get(i).des+ ",   ");
                   System.out.print("Product  Price      : " + pl.get(i).price+ ",   ");


                 }   

            }
            menu();
        }
        //Method to Edit Category
        public void editCat()
        {
            String cat,cname;
            int count=0;
            System.out.println("Select Category ID from the following categories");

            if(cl.size()==0)
                System.out.println("");
            else
            {
                for(int i=0;i<cl.size();i++)
                {
                    System.out.println("Category ID: "+cl.get(i).catID+" Category Name:"+cl.get(i).cname);

                }
            }
            System.out.println("Enter the Category ID of the Category you want to edit");
            cat=in.next();
            for(int i=0;i<cl.size();i++)
            {
                if(cat.equals(cl.get(i).catID))
                {
                    System.out.println("Current name of Category is:"+cl.get(i).cname);
                    System.out.println("Enter the new name you want to edit");
                    cname=in.next();
                    cl.get(i).cname=cname;
                    break;
                }
                else
                    count++;
            }
            if(count==cl.size())
            {
                System.out.println("The Category ID ypu entered is either invalid or not present.Please enter a valid ID");
                editCat();
            }
            menu();

        } 
        //method to Edit Product
        public void editProd()
        {
            String prod,des;
            int count=0,index;
            System.out.println("Enter Product ID    :");
            prod=in.next();
            for(int i=0;i<pl.size();i++)
            {
                if(prod.equals(pl.get(i).prodID))
                {
                    index=i;
                    System.out.print("Category ID         : " + pl.get(i).catID + ",  ");
                    System.out.print("Product  ID         : " + pl.get(i).prodID+ ",   ");
                    System.out.print("Product  Name       : " + pl.get(i).pName+ ",   ");
                    System.out.print("Product Description : " + pl.get(i).des+ ",   ");
                    System.out.println("Product  Price      : " + pl.get(i).price+ ",   ");
                    System.out.println("Enter the description you want to edit");
                    des=in.next();
                    //change the minimum to 50 before submission
                    while(Pattern.matches("[a-zA-Z]{50,}",des)!=true)
                    {
                    System.out.println("The description should be minimum 50 characters");
                    des=in.next();
                    }
                    pl.get(i).des=des;
                    break;
                }
                else
                    count++;
            }
            if(count==pl.size())
            {
                System.out.println("Enter a valid product ID,The ID you entered does not exist");
                editProd();
            }
            menu();
            //can edit the description of the product
        } 

       //Method to display Menu
        public void menu() {

            System.out.println("1.Add Product");
            System.out.println("2.Add Product Category");
            System.out.println("3.Display categories");
            System.out.println("4.Display products");
            System.out.println("5.Edit Category");
            System.out.println("6.Edit Product");
            System.out.println("7.Exit");


            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    addProductCat();
                    break;
                case 3:
                    displayCat();
                    break;
                case 4:
                    displayProd();
                    break;
                case 5:
                     editCat();
                     break;
                case 6:
                     editProd();
                     break;
                case 7:
                      return;			

                default:
                    System.out.println("Enter choice from 1-7");

            }

        }

        public static void main(String args[]) {
            System.out.println("---------------------------PRODUCT MANAGEMENT------------------------------------");
            DisMenu d =new DisMenu();
            d.menu();

        }
    }
