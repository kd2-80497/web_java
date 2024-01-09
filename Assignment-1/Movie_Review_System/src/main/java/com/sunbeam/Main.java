/**
 * 
 */
package com.sunbeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.daos.MoviesDao;
import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.daos.SharesDao;
import com.sunbeam.daos.UserDao;
import com.sunbeam.pojos.Movies;
import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DateUtil;

/**
 * @author Ritu And Ujjwal prn no ritu = 230940820086 prn no ujjwal =
 *         230940820111
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int choice = 1;
		while (choice != 0) {
			System.out.print("\n0. Exit\n1. Sign Up\n2. Sign In\nEnter your Choice = ");

			choice = sc.nextInt();
			switch (choice) {
			case 0:
				System.out.println("Bye Bye");
				break;
			case 1: {
				// Adding new user into database
				Users u = new Users();
				int x = u.accept();
				if (x == 1) {

					try (UserDao ud = new UserDao()) {
						ud.adduser(u);
						System.out.println("User Added");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else
					System.out.println("User not Added...");
			}
				break;
			case 2: {
				/**
				 * @author Ritu And Ujjwal prn no ritu = 230940820086 prn no ujjwal =
				 *         230940820111
				 *
				 */

				// user login code

				List<Users> list = new ArrayList<Users>();
				Users us = new Users();
				try (UserDao ud = new UserDao()) {
					System.out.print("Enter email = ");
					us.setEmail(sc.next());
					System.out.print("Enter password = ");
					us.setPassword(sc.next());

					// validating user credentials
					list = ud.valid(us);
					if (list.size() == 0) {
						System.out.println("User Not Found...");
						break;
					} else {
						int ch = 1;
						while (ch != 0) {
							System.out.print("\n0. Log Out\n" + "1. Edit Profile\n" + "2. Change Password\n"
									+ "3. Write a Review\n" + "4. Edit Review\n" + "5. Display all Movies\n"
									+ "6. Display all Reviews\n" + "7. Display my Reviews\n"
									+ "8. Display Reviews shared with me\n" + "9. Share a Review\n"
									+ "10. Delete a Review\nEnter Choice = ");

							ch = sc.nextInt();
							switch (ch) {
							case 0:
								ch = 0;
								System.out.println("Logout Succussful...");
								break;
							case 1: {
								// Edit Profile

								System.out.print("Enter First Name = ");
								String fn = sc.next();
								int i = 0;
								if (fn.length() == 0) {
									System.out.println("Name cannot be empty.");
									break;
								}
								while (i != fn.length()) {
									// validating if first name contains number
									if (!Character.isAlphabetic(fn.charAt(i))) {
										System.out.println("Enter only Alphabets.");
										break;
									}
									i++;

								}
								if (i != fn.length())
									break;
								System.out.print("Enter Last Name = ");
								String ln = sc.next();
								int j = 0;
								while (j != ln.length()) {
									// validating if last name contains number
									if (!Character.isAlphabetic(ln.charAt(j))) {
										System.out.println("Enter only Alphabets.");
										break;
									}
									j++;

								}

								System.out.print("Enter Password = ");
								sc.nextLine();
								String s = sc.nextLine();

								// Password validation of minimum 8 characters
								if (s.length() < 8) {
									System.out.println("Password should be minimum 8 characters.");
									break;
								}

								System.out.print("Enter Mobile = ");
								String m = sc.next();
								if (m.length() != 10) {
									System.out.println("Mobile should be 10 digit.");
									break;
								}
								i = 0;
								while (i != 10) {
									if (!Character.isDigit(m.charAt(i))) {
										System.out.println("Enter only numbers.");
										break;
									}
									i++;

								}

								System.out.print("Birth Date (dd-MM-yyyy): ");
								String dateStr = sc.next();

								try (UserDao u = new UserDao()) {
									u.editProfile(fn, ln, m, s, DateUtil.parse(dateStr), list.get(0).getId());
									System.out.println("Profile Updated...");
								}
							}
								break;
								
							case 2: {
								
								// Resetting Password
								System.out.print("Enter old Password = ");
								String pass = sc.nextLine();
								sc.nextLine();
								System.out.print("Enter new Password = ");
								pass = sc.nextLine();
								try (UserDao u = new UserDao()) {
									u.update(pass, list.get(0).getId());
								}
							}
								break;
								
							case 3: {

								// Adding movie review

								List<Movies> mlist = new ArrayList<Movies>();
								try (MoviesDao md = new MoviesDao()) {
									mlist = md.display();
									for (Movies movie : mlist) {
										System.out.println(movie.toString());
									}
								}
								Reviews r = new Reviews();
								
								if(r.accept()==0) {
									break;
								}
								try (ReviewsDao rd = new ReviewsDao()) {
									System.out.println("Review Saved: " + rd.accept(r, list.get(0).getId()));
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
								break;
								
							case 4: {
								// Editing logged in user reviews

								List<Reviews> rlist = new ArrayList<Reviews>();
								try (ReviewsDao rdao = new ReviewsDao()) {
									rlist = rdao.myReviews(list.get(0).getId());
									for (Reviews r : rlist) {
										System.out.println(r.toString());
									}
								}
								System.out.print("Enter review id = ");
								int id = sc.nextInt();
								System.out.print("Enter New Review = ");
								sc.nextLine();
								String s = sc.nextLine();
								if(s.length()==0) {
									System.out.println("Review cannot be empty.");
									break;
								}
								int c = s.indexOf(" ");
								if(c==-1) {
									System.out.println("Review cannot be single word.");
									break;
								}
								if(c==s.length()-1) 
									{
									System.out.println("Review is short");
									break;
									}
								
								
								try (ReviewsDao rd = new ReviewsDao()) {
									System.out.println("Review updated: " + rd.edit(id, s, list.get(0).getId()));
								}
							}
								break;
								
							case 5: {
								// display list of all movies

								List<Movies> mlist = new ArrayList<Movies>();
								try (MoviesDao md = new MoviesDao()) {
									mlist = md.display();
									for (Movies movie : mlist) {
										System.out.println(movie.toString());
									}
								}

							}
								break;
							case 6: {
								// display list of all movie reviews

								List<Reviews> rlist = new ArrayList<Reviews>();
								try (ReviewsDao rd = new ReviewsDao()) {
									rlist = rd.display();
									for (Reviews r : rlist) {
										System.out.println(r.toString());
									}
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
								break;
								
							case 7: {
								// display list of all logged in user reviews

								List<Reviews> rlist = new ArrayList<Reviews>();
								try (ReviewsDao rdao = new ReviewsDao()) {
									rlist = rdao.myReviews(list.get(0).getId());
									for (Reviews r : rlist) {
										System.out.println(r.toString());
									}
								}
							}
								break;
							case 8: {
								// display shared reviews
								List<Reviews> rlist = new ArrayList<Reviews>();
								try (ReviewsDao rdao = new ReviewsDao()) {
									rlist = rdao.displaySharedReview(list.get(0).getId());

									for (Reviews r : rlist) {
										System.out.println(r.toString());
									}
								}
							}
								break;

							case 9: {

								// Below code shows all reviews of logged in user

								List<Reviews> listr = new ArrayList<Reviews>();
								try (ReviewsDao rdao = new ReviewsDao()) {
									listr = rdao.myReviews(list.get(0).getId());
									for (Reviews r : listr) {
										System.out.println(r.toString());
									}
								}

								System.out.print("Enter Review id to be shared = ");
								int rid = sc.nextInt();
								for (Reviews reviews : listr) {
									if (reviews.getId() == rid) {
										List<Reviews> rlist = new ArrayList<Reviews>();
										try (ReviewsDao rdao = new ReviewsDao()) {
											rlist = rdao.mySharedReviews(list.get(0).getId(), rid);
											for (Reviews r : rlist) {
												System.out.println(r.toString());
											}

											@SuppressWarnings("resource")
											UserDao udao = new UserDao();
											List<Users> ulist = new ArrayList<Users>();

											ulist = udao.display();
											for (Users users : ulist) {
												System.out.println(users.toString());
											}
											System.out.print("Enter user ids to share review with (enter 0 to end) = ");
											ArrayList<Integer> num = new ArrayList<>();
											int x = sc.nextInt();
											while (x != 0) {
												num.add(x);
												x = sc.nextInt();
											}
											num.add(0);
											int self = 0;
											int i = 0;

											// validating share review cannot be shared with own and cannot share others
											// review
											while (num.get(i) != 0) {
												try (SharesDao sdao = new SharesDao()) {
													if (num.get(i) == list.get(0).getId()) {
														self++;
														System.out.println(
																"You can't share your own review with yourself.");
														i++;
														continue;
													}
													sdao.add(rid, num.get(i));
													i++;
												}

											}
											System.out.println("Review shared with " + (num.size() - 1 - self)
													+ " users successfully.");
											break;
										}

									} 

								}

							}
								break;
							case 10: {
								System.out.print("Enter Review_ID = ");
								int id = sc.nextInt();
								try (ReviewsDao rd = new ReviewsDao()) {
									int x = rd.delete(id, list.get(0).getId());
									if (x == 0) {
										System.out.println("you can delete only your reviews");
									} else {
										try (SharesDao sdao = new SharesDao()) {
											sdao.delete(id);
											System.out.println("Reviews Deleted: " + x);
										}

									}
								}
							}
								break;
							}
						}
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			}break;
			default: System.out.println("Entered wrong choice...");
			break;
			}
		}
		sc.close();
	}
}
