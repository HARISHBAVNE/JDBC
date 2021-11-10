import java.util.*;
import java.io.*;
import java.sql.*;

class DB
{
	private Connection conn;
	private ResultSet rs;
	
	public DB()
	{
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppa","root",""); // Connection for Database
		}
		catch(Exception e){}
	}
	public void select()
	{
		try
		{
			String sql = "select * from Student";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.print("RollNo:"+rs.getInt("RollNo"));
				System.out.print(",Name:"+rs.getString("Name"));
				System.out.print(",Address:"+rs.getString("Address"));
				System.out.println(",Marks:"+rs.getInt("Marks"));
			}
		}
		catch(Exception e){}
	}
	public void insert(int RollNo,String Name,String Address,int Marks)
	{
		try
		{
			String sql = "insert into student (RollNo,Name,Address,Marks)values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,RollNo);
			stmt.setString(2,Name);
			stmt.setString(3,Address);
			stmt.setInt(4,Marks);

			int Record = stmt.executeUpdate();
			if(Record > 0)
			{
				System.out.println("Record inserted successfully.");
			}
			else
			{
				System.out.println("Record not inserted.");	
			}

		}
		catch(Exception e){}
	}
	public void update(String Name,int RollNo)
	{
		try
		{
			String sql = "update student set name=? where RollNo=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,Name);
			stmt.setInt(2,RollNo);

			int Record = stmt.executeUpdate();
			if(Record>0)
			{
				System.out.println("Record update Successfully.");
			}
			else
			{
				System.out.println("Not update");
			}
		}
		catch(Exception e){}
	}
	public void delete(int RollNo)
	{
		try
		{
			String sql = "delete from Student where RollNo=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,RollNo);

			int Record = stmt.executeUpdate();
			if(Record > 0)
			{
				System.out.println("Recoed Deleted successfully.");
			}
			else
			{
				System.out.println("Recoed not Deleted.");
			}
		}
		catch(Exception e){}
	}
	
}
class DataBase
{
	public static void main(String arg[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int RollNo=0,Marks=0;
		String Name;
		String Address;
		DB obj = new DB();
		int choice = 1;
		String str;
		while(choice !=0)
		{
			System.out.println("\n=========================================\n");
			System.out.println("===========JDBC APLLICATION==============\n");
			System.out.println("1:Read(Show data from record from table)");
			System.out.println("2:Create(Insert new record into table.)");
			System.out.println("3:Update(update record from table)");
			System.out.println("4:Delete(Delete record from table)");
			System.out.println("5:Exit.");
			System.out.println("\n=========================================\n");

			System.out.print("Please enter your choice:");
			choice = Integer.parseInt(br.readLine());
			switch(choice)
			{
				case 1:
					obj.select();
					break;
				case 2:
					System.out.println("Enter Roll No:");
					RollNo = Integer.parseInt(br.readLine());
					System.out.println("Enter Name:");
					Name = br.readLine();
					System.out.println("Enter Address:");
					Address = br.readLine();
					System.out.print("Enter Marks:");
					Marks = Integer.parseInt(br.readLine());

					obj.insert(RollNo,Name,Address,Marks);
					break;
				case 3:
					System.out.println("Enter Name to update:");
					Name = br.readLine();
					System.out.print("Enter Roll No:");
					RollNo = Integer.parseInt(br.readLine());					
					obj.update(Name,RollNo);
					break;
				case 4:
					System.out.print("Enter Roll No:");
					RollNo = Integer.parseInt(br.readLine());
					obj.delete(RollNo);
					break;
				case 5:
					System.out.println("Thank you for using JDBC application.");
					System.exit(0);
				default:
					System.out.println("Please provide correct choice.");
			}
		}

	}
}