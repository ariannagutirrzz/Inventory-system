package programa;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("#.00");
		
		int productos = 0;
		int opc;
		
		double tasaDolar = 35.0;
		double iva = 0.16;
		
		String[] arrNombres = null;
		String[] arrCodigo = null;
		double[] arrPrecios = null;
		int[] arrCantidad = null;
		
		do {
			System.out.println("--------Menu--------");
			System.out.println("1. Ingrese la cantidad del producto. ");
			System.out.println("2. Editar producto. ");
			System.out.println("3. Ver producto. ");
			System.out.println("4. Estadistica. ");
			System.out.println("5. Configuraciones. ");
			System.out.println("6. Salir. ");
			opc = sc.nextInt();
			
			boolean opcionUnoCompletada;
			
			switch(opc) {
			case 1: 
				System.out.println("Ingrese el numero de productos a ingresar: ");
				productos = sc.nextInt();
				
				if(productos > 0) {
					
					arrNombres = new String[productos];
					arrCodigo = new String[productos];
					arrPrecios = new double[productos];
					arrCantidad = new int[productos];
					
					for(int i=0; i<productos; i++) {
						System.out.print("Ingresa el nombre del producto " + (i+1) + ": ");
						arrNombres[i] = sc.next();
						
						sc.nextLine();					
						boolean verificacionDeCodigo;
						
						//validacion para evitar que el codigo se repita
						
						do { 
							 System.out.print("Ingresa el codigo del producto " + (i + 1) + ": ");
					            	arrCodigo[i] = sc.next();

						            verificacionDeCodigo = false;

						            for (int j = 0; j < i; j++) { 
						                if (arrCodigo[i].equals(arrCodigo[j])) { // Compara cadenas usando equals
						                    System.err.println("El código ingresado ya está en uso.");
						                    verificacionDeCodigo = true;
						                    break;
						                }
						            }
						} while(verificacionDeCodigo == true);
						
						//Validacion para precio del producto
						
						do {
							System.out.print("Ingresa el precio del producto " + (i+1) + ": ");
							if(sc.hasNextDouble()) {
								arrPrecios[i] = sc.nextDouble();
								
								if(arrPrecios[i] <= 0) {
									System.err.println("El precio ingresado debe ser mayor a 0");
								}
								
							} else {
								 System.err.println("Ingresa un valor de tipo double.");
							     sc.next();
							}
								
						} while(arrPrecios[i] <= 0);
						
						//Validacion para que la cantidad de productos no sea menor a 0 y para que solo acepte valores enteros
						
						do {
							System.out.print("Ingresa la cantidad del producto " + (i+1) + ": ");
							if(sc.hasNextInt()) {
								arrCantidad[i] = sc.nextInt();
								
								if(arrCantidad[i] < 0) {
									System.err.println("La cantidad del producto debe ser un numero mayor o igual a 0");
								}
							} else {
								System.err.println("Ingresa un valor de tipo entero.");
							     sc.next();
							     
							     //El while daba problemas al evaluar el String, y con lo siguiente se le fuerza a repetir
							     arrCantidad[i] = -1;
							}
								
						} while(arrCantidad[i] < 0);
					
					};
				} else {
					System.out.println("Ingresa al menos un producto");
				}
				
				opcionUnoCompletada = true;
				break;
			case 2: 
				
				if(productos == 0 ) {
					System.err.println("El inventario esta vacio. Primero añade algunos productos.");
					break;
				}
				
				System.out.println("Elige el numero del producto que desee editar: ");
				System.out.println();

				for(int i=0; i<arrNombres.length; i++) {
					System.out.println( "Poducto " + (i+1));
					System.out.println( "Nombre: " + arrNombres[i] );
					System.out.println( "Precio: " + arrPrecios[i] + "$");
					System.out.println( "Cantidad: " + arrCantidad[i] );
					System.out.println( "Codigo: " + arrCodigo[i] );
					System.out.println();

				}
					
				int posicionProducto = sc.nextInt();
				if(posicionProducto >= 1 && posicionProducto <= arrNombres.length) {
					System.out.println("Seleccionaste el producto " + posicionProducto);
					
					  System.out.println("Elige qué deseas editar:");
					    System.out.println("1. Nombre");
					    System.out.println("2. Precio");
					    System.out.println("3. Cantidad");
					    System.out.println("4. Código");
					    
					    int opcionEditar = sc.nextInt();
						switch (opcionEditar) {
						case 1:
							
							System.out.print("Ingrese el nuevo nombre para el producto: ");
							String nuevoNombre = sc.next();
							
							sc.nextLine();
							
							arrNombres[posicionProducto - 1] = nuevoNombre;
							System.out.println("Nombre actualizado con éxito.");
							break;
							
						case 2:
							
							System.out.print("Ingresa el nuevo precio para el producto: ");
				            double nuevoPrecio = sc.nextDouble();
				            
				            arrPrecios[posicionProducto - 1] = nuevoPrecio;
							System.out.println("Precio actualizado con éxito.");
							break;
				
						
						case 3:
							
							System.out.print("Ingresa la nueva cantidad para el producto: ");
				            int nuevaCantidad = sc.nextInt();
				            
				            arrCantidad[posicionProducto - 1] = nuevaCantidad;
							System.out.println("Cantidad actualizada con éxito.");
							break;
							
						case 4:
							boolean verificarCodigo = false;
							
							System.out.print("Ingresa el nuevo codigo para el producto: ");
				            String nuevoCodigo = sc.next();
				            
				            
				            for(int k=0; k<arrCodigo.length;k++) {
				            	if(arrCodigo[k].equals(nuevoCodigo)) {
				            		verificarCodigo = true;
				            		break;
				            	} 
				            }
				            
				            if (verificarCodigo == true) {
		                		System.err.println("El codigo ingresado ya existe.");
		                		break;
		                	} 
				            
				            arrCodigo[posicionProducto - 1] = nuevoCodigo;
							System.out.println("Codigo actualizado con éxito.");
							
							break;
							
						}
				} else {
					System.err.println("Numero de producto no existe");
				}
				
				break;
			case 3: 
				
					boolean validarCodigo = false;
				
				if(productos == 0 ) {
					System.err.println("El inventario esta vacio. Primero añade algunos productos.");
					break;
				}
					
						 System.out.println("Ingresa el codigo del producto: ");
						 	String codigoProducto = sc.next();
				            
				            for (int j = 0; j < arrCodigo.length; j++) { 
				                if (codigoProducto.equals(arrCodigo[j])) { 
				                    System.out.println("Nombre: " + arrNombres[j]);
				                    System.out.println("Precio: " + df.format(arrPrecios[j]));
				                    System.out.println("Cantidad: " + arrCantidad[j]);	
				                    validarCodigo = true;
				                } 
				            }
				            
				            if (validarCodigo == false) {
		                		System.err.println("El codigo ingresado no existe.");
		                	} 
				break;
			case 4:
				
				boolean validarProducto = false;
				
				if(productos == 0 ) {
					System.err.println("El inventario esta vacio. Primero añade algunos productos.");
					break;
				}
				System.out.println("Total de los productos: " + productos);
				double productoMasCaro = 0;
				double productoMasBarato = Double.MAX_VALUE;
				int indiceProductoMasCaro = -1; 
				for (int i = 0; i < arrPrecios.length; i++) {
					if(productoMasCaro < arrPrecios[i]) {
						productoMasCaro = arrPrecios[i]; 
					}
				    if (productoMasCaro == arrPrecios[i]) {
				        indiceProductoMasCaro = i;
				    }
				}

				if (indiceProductoMasCaro != -1) {
				    System.out.println("El producto mas caro es: " + arrNombres[indiceProductoMasCaro]);
				    System.out.println("Precio: " + df.format(productoMasCaro));
				    System.out.println();
				} else {
				    System.out.println("No hay productos en el inventario");
				}
				
				
				int indiceProductoMasBarato = -1; 
				for (int i = 0; i < arrPrecios.length; i++) {
					if (productoMasBarato > arrPrecios[i]) {
						productoMasBarato = arrPrecios[i];
					}
				    if (productoMasBarato == arrPrecios[i]) {
				        indiceProductoMasBarato = i;
				    }
				}

				if (indiceProductoMasBarato != -1) {
				    System.out.println("El producto mas barato es: " + arrNombres[indiceProductoMasBarato]);
				    System.out.println("Precio: " + df.format(productoMasBarato));
				    System.out.println();

				} else {
				    System.out.println("No hay productos en el inventario");
				}
//					
					System.out.println("Los productos en 0 son: ");
					for(int i=0; i<arrCantidad.length; i++) {
						if (arrCantidad[i] == 0) {
							System.out.println("Producto: " + arrNombres[i]);
						    System.out.println("Cantidad: " + arrCantidad[i]);
						     validarProducto = true;
						} 
					}
					
					if (validarProducto != true) {
                		System.err.println("No hay productos en 0.");
                	} 
				    System.out.println();
				
					double totalNeto = 0;
					double totalBruto = 0;
					
					//calculo del total bruto
					for(int i=0; i < arrPrecios.length; i++) {
			            double precioBruto = arrPrecios[i] * arrCantidad[i];
			            totalBruto += precioBruto;
			            
			          }
					totalNeto = totalBruto + (totalBruto*iva); 
			          System.out.println("El total bruto es: " + df.format(totalBruto) + "$" + " // " + df.format((totalBruto * tasaDolar)) + "Bs.");
			          System.out.println("El total neto es: " + df.format(totalNeto) + "$" + " // "+ df.format((totalNeto * tasaDolar)) + "Bs.");

			            System.out.println();

					//calculo total de inventario	
						
					double sumaPrecios = 0;
					int sumaCantidad = 0;
					
					for(int i=0; i< arrPrecios.length; i++) {
						double precioProducto = arrPrecios[i];
						int cantidadProducto = arrCantidad[i];
						
						sumaPrecios += precioProducto;
						sumaCantidad += cantidadProducto;
					}
						System.out.println("Total del Inventario: ");
						System.out.println("Suma de Precios: " + df.format(sumaPrecios) + "$" + " // " + df.format((sumaPrecios * tasaDolar)) + "Bs.");
						System.out.println("Cantidad total: " + df.format(sumaCantidad) + " productos");
				break;
			case 5:
				
				if(productos == 0 ) {
					System.err.println("El inventario esta vacio. Primero añade algunos productos.");
					break;
				}
				System.out.println("Configuraciones.");
				System.out.println("1. Configurar IVA");
			    System.out.println("2. Configurar tasa del dólar");
			    System.out.println("3. Volver al menú principal");
			    
			    int configuracion = sc.nextInt();
			    
			    switch(configuracion) {
			    case 1: 
			    	System.out.println("Ingrese el nuevo valor del iva: ");
			    	iva = sc.nextDouble();	
					System.out.println("Iva cambiado con exito a: " + iva);
				    iva = iva/100;
			    	break;
			case 2:
				System.out.print("Ingrese la nueva tasa del dólar: ");
				 tasaDolar = sc.nextDouble();
				 System.out.println("Tasa dolar cambiada con exito a: " + tasaDolar);
				break;
			case 3:
				break;
				
				default:
					System.err.println("Opción no válida");
					break;
		}
			case 6:
				System.out.println("Chau");
				break;
			
			default: System.out.println("La opcion que escogiste no existe");
			break;
			}
			
		} while (opc != 6);
		
		}
		
	};
	

