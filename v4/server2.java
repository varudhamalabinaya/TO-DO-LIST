import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

class Task{
    String name;
    boolean complete;
    Task(String name){
        this.name=name;
        this.complete=false;
    }
}

public class server2 {
     static ArrayList<Task>todoList=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //this will create a server . will start when you call this method using 8000 port number
        HttpServer server=HttpServer.create(new InetSocketAddress(9001),0);
       
        //Adding Task
        //this is the place where the add request will reach in correct place.
        server.createContext("/add",new HttpHandler(){
            @Override
            public void handle(HttpExchange exchange)throws IOException{
                //exchange is the object that represent the request and response 
                //read the task
                String query=exchange.getRequestURI().getQuery();
                //will get the complete URL which client gave and getQuery will take the query part after ? (for example ?task=buy milk)
                String task=query.split("=")[1];
                // it will get the query and split so we can easily get the task part

                //store the task
                todoList.add(new Task(task));

                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");


                //sending response
                String response="task which is added "+task;
                exchange.sendResponseHeaders(200, response.length());
                //200 for the success , response.length the server wants to know the byte size before sending the response .
                OutputStream os=exchange.getResponseBody();
                //getREsposeBody will give you the channel to send the response to the client 
                os.write(response.getBytes());
                // the response msg which we send to the client the getbytes() are the bytes or path where the response will send
                os.close();
            }
        });
        

        //Delete Task
        server.createContext("/delete",new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange)throws IOException{
                String query=exchange.getRequestURI().getQuery();
                int index = Integer.parseInt(query.split("=")[1]);
                //to delete the task
                
                Task removeTask=todoList.remove(index-1);
                //the values will get stored from 1 to n but the arraylist index starts from 0 so we will use n-1 
                //sending response

                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");


                String response="Task deleted: "+removeTask.name;
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os=exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                
            }
        });
        //view Task
        // View Task
        server.createContext("/view", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < todoList.size(); i++) {
                    Task task = todoList.get(i);
                    sb.append((i + 1) + ". " + task.name);
                    if (task.complete) {
                        sb.append(" [COMPLETED]");
                    }
                    sb.append("\n");
                }
                //String builder is used to create multiple string together.

                String response = sb.toString();
                if (response.isEmpty()) {
                    response = "No task is available";
                }


                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");


                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }); 
        //Mark as Complete 
        server.createContext("/complete",new HttpHandler(){
            @Override
            public void handle(HttpExchange exchange)throws IOException{
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin","*");
                //CORS (anyone can access my API so there will be no blocking)
                String query=exchange.getRequestURI().getQuery();
                int index = Integer.parseInt(query.split("=")[1]);

                //to mark the task as complete
                 Task task = todoList.get(index - 1);
                 task.complete = true;

                // sending the response to the client 
                String response="Task marked as complete: "+task.name;
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }

        });
        //undo option
        server.createContext("/undo",new HttpHandler(){
                @Override
                public void handle(HttpExchange exchange)throws IOException{
                    exchange.getResponseHeaders().add("Access-Control-Allow-Origin","*");
                     String query=exchange.getRequestURI().getQuery();
                    int index = Integer.parseInt(query.split("=")[1]);
                     Task task = todoList.get(index - 1);
                 task.complete = false;

                 String response="Task marked as pending: "+task.name;
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();


                }
            
        });
        // this is the edit function 
        server.createContext("/edit",new HttpHandler(){
                @Override
                public void handle(HttpExchange exchange)throws IOException{
                    exchange.getResponseHeaders().add("Access-Control-Allow-Origin","*");
                    //gets the Query from the user
                     String query=exchange.getRequestURI().getQuery();
                     //the Query will be seperated by '&'like index=2&buy milk-> params=index=2 each Query is isolated 
                     //params for extracting the multiple values from the  URL 
                    String []params=query.split("&");
                    //this tells what task to edit 
                     int index=Integer.parseInt(params[0].split("=")[1]);
                     //updated task name
                     String newTaskName=params[1].split("=")[1];
                    Task task=todoList.get(index-1);
                    //used to modify the task name like assigning new name to old one 

                    task.name=newTaskName;

                 String response="Task got edited: "+task.name;
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();


                }
            
        });

        // this part need to be at the end of all the functions so all the func which we create before this will egt started
        server.start();
        System.out.println("Server started on port 9001");
    }
     
}
