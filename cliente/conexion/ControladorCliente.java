package conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Timer;


public class ControladorCliente {
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	// DATOS NECESARIOS PARA LA CONEXION
	private static final String host ="192.168.1.107";
	private static final int puerto = 5000;
	private int jugador;
	private Object objLeido;
	private int idUsuario;


	public static void main(String[] args) {
		
		// GENERAMOS LA CONEXION
		try {
			ControladorCliente cc = new ControladorCliente();
			//cc.jugador = 0;
			cc.socket = new Socket(host,puerto);
			cc.out = new ObjectOutputStream( cc.socket.getOutputStream());
			cc.in = new ObjectInputStream( cc.socket.getInputStream() );
			cc.HiloDeJuego();
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public ObjectOutputStream getOut() {
		return out;
	}

	public Object escuchar(){
		try {
			System.out.println(in.toString());
			Object peticion = in.readObject();
			objLeido = peticion;
			return peticion;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void enviarMensaje( Object obj ){
		try {
			out.reset(); // USAR SIEMPRE
			out.writeObject(obj);
			//out.reset(); // USAR SIEMPRE
			out.flush();
			
			objLeido = (Object)in.readObject();
			if( objLeido instanceof Integer ){
				jugador = (Integer)objLeido; // REPRESENTA AL JUGADOR EN JUEGO
			}
			System.out.println(objLeido);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getJugador() {
		return jugador;
	}

	public void setJugador(int jugador) {
		this.jugador = jugador;
	}

	public Object leerMensaje(){
		return objLeido;
	}
	
	public ObjectInputStream getIn() {
		return in;
	}

	public void cerrarSocket(){
		try {
			enviarMensaje("KILL THREAD");
			out.close();
			in.close();					
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int id) {
		this.idUsuario = id;
	}
	
	public void HiloDeJuego(){
		Thread hiloDeJuego = new Thread( new Runnable(){
			public void run(){
				
				while( true ){
				
					Object peticion = clientSocket.escuchar();
		
					if( peticion instanceof EscenarioBean ){
						System.out.println("Se recibio el escenario");
				
						dibujarEscenario();
						//direccion = 0;
					}else if( peticion.equals("DIRECCION")){
						System.out.println("NOS PIDIERON DIRECCION");
						try {
							
							clientSocket.getOut().writeObject(new DireccionBean(direccion,clientSocket.getJugador(), idPartida ));
							clientSocket.getOut().flush();
							System.out.println("ENVIAMOS DIRECCION : " + direccion);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// ARREGLAR.. NO COORDINA BIEN
						timer = new Timer(1000, new ActionListener() {			
							int elapsedSeconds = 3;
							@Override
							public void actionPerformed(ActionEvent e) {
								elapsedSeconds--;
						        lblTimer.setText(Integer.toString(elapsedSeconds));
						        if(elapsedSeconds == 0){
						           elapsedSeconds = 3;
						           timer.restart();
						        }
							}
						});
						timer.start();
					}else if( peticion.equals("TERMINO")){
						/*System.out.println("SE TERMINO LA PARTIDA");
						lobby.setVisible(true);
						try {
							
							dispose();
							System.out.println(" CIERRO ESCENARIO");
							this.finalize();	
							break;
						} catch (Throwable e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					}

				}// FIN WHILE TRUE
				
			}
		});
		hiloDeJuego.start();
	}


}
