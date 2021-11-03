package com.saulo.jsonExamples;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.CDL;
import org.json.Cookie;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;
import org.json.JSONWriter;
import org.json.Property;
import org.json.XML;

//Nesses exemplos foi usada a biblioteca org.json e não a json.simple passada em aula

public class JsonExamples {

	// Método principal para executar os outros métodos
	// Para executar um método específico, basta descomentá-lo abaixo e rodar o
	// programa
	public static void main(String[] args) {

//		JSONExampleArray1();
//		JSONExampleArray2();
//		JSONExampleStringer();
//		JSONExampleObject1();
//		JSONExampleObject2();
//		JSONExampleObject3();
//		JSONExamplWriter();		
//		jsonExampleTokener();
//		JSONObjectToArray();
//		XMLToExampleConversion();
//		XMLFromExampleConversion();
//		CookieToExampleConversion();
//		CookieFromExampleConversion(); 
//		HTTPToExampleConversion();
//		HTTPFromExampleConversion();
//		CDLToExampleConversion();
//		CDLFromExampleConversion();
//		PropertyToExampleConversion();
//		PropertyFromExampleConversion();
	}

	// Criar um objeto do tipo JSONObject através de uma classe JSONArray usando uma
	// String
	private static void JSONExampleArray1() {

		// Setando o conteúdo da string (que será um array do tipo json)
		// As barras \ servem para 'escapar' o próximo caractere, no caso a "
		// Ou seja, ela é impressa na string
		String arrayStr = "[" + "true," + "false," + "\"true\"," + "\"false\"," + "\"hello\"," + "23.45e-4,"
				+ "\"23.45\"," + "42," + "\"43\"," + "[" + "\"world\"" + "]," + "{" + "\"key1\":\"value1\","
				+ "\"key2\":\"value2\"," + "\"key3\":\"value3\"," + "\"key4\":\"value4\"" + "}," + "0," + "\"-1\""
				+ "]";

		// Através do construtor da classe JSONArray foi criado o objeto array através
		// da String acima
		JSONArray array = new JSONArray(arrayStr);
		System.out.println("Values array: " + array);

		// Antes de criar é necessário criar uma lista contendo as chaves (numéricos
		// sequenciais) para os valores.
		JSONArray list = listNumberArray(array.length());
		System.out.println("Label Array: " + list.toString());

		// Convertendo o array para JSONObject utilizando as chaves da lista e os
		// valores do array.
		JSONObject object = array.toJSONObject(list);
		System.out.println("Final JSONOBject: " + object);
	}

	private static void JSONExampleArray2() {

		// Também é possível criar um objeto JSONArray vazio e depois adicionar os
		// elementos
		JSONArray array = new JSONArray();

		// Adicionando os elementos com o .put()

		array.put("value");
		array.put(5);
		array.put(-23.45e67);
		array.put(true);

		// Usando o método listNumberArray desta classe, as chaves foram criadas

		JSONArray list = listNumberArray(array.length());

		// Foi criado o objeto da classe JSONObject através do array (valores) e da list
		// (chaves)
		JSONObject object = array.toJSONObject(list);
		System.out.println("Final JSONOBject: " + object);
	}

	// Esse método cria um JSONArray que servirá para ser as chaves (numéricas) do
	// json
	private static JSONArray listNumberArray(int max) {

		JSONArray res = new JSONArray();
		for (int i = 0; i < max; i++) {
			// Para funcionar, o tipo deve ser String
			res.put(String.valueOf(i));
		}
		return res;
	}

	// Usando a classe JSONStringer
	private static void JSONExampleStringer() {

		// Instanciando um objeto da classe JSONStringer

		JSONStringer jsonStringer = new JSONStringer();

		// O .object() é um método que serve para adicionar as chaves e os valores (deve
		// ser fechado depois)

		jsonStringer.object();

		// Adicionando as chaves (.key) e os valores (.value) ao objeto jsonStringer

		jsonStringer.key("trueValue").value(true);
		jsonStringer.key("falseValue").value(false);
		jsonStringer.key("nullValue").value(null);
		jsonStringer.key("stringValue").value("hello world!");
		jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
		jsonStringer.key("intValue").value(42);
		jsonStringer.key("doubleValue").value(-23.45e67);

		// fechando o .object() com .ednObject

		jsonStringer.endObject();

		// Transformando o conteúdo de jsonStringer em String
		String str = jsonStringer.toString();

		// Criando o objeto jsonObject através do método construtor da classe JSONObject
		JSONObject jsonObject = new JSONObject(str);

		System.out.println("Final JSONOBject: " + jsonObject);
	}

	// JSONObject
	private static void JSONExampleObject1() {

		// É possível criar um JSONObject através do método construtor passando como
		// parâmetro uma String

		// Criando a String
		String string = "{\"0\":\"value\",\"1\":5,\"2\":-2.345E68,\"3\":true}";

		// Criando o JSONObject
		JSONObject example = new JSONObject(string);
		System.out.println("Final JSONObject: " + example);

	}

	// JSONObject
	private static void JSONExampleObject2() {

		// Outra forma de criar um JSONObject sem utilizar uma String
		JSONObject example = new JSONObject();

		// Adicionando as chaves e valores respectivos
		// Os valores não podem ser do tipo null
		example.put("key", "value");
		example.put("key2", 5);
		example.put("key3", -23.45e67);
		example.put("trueValue", true);

		System.out.println("Final JSONOBject: " + example);
	}

	private static void JSONExampleObject3() {

		// Além da String, também é possível criar um JSONObject através de um objeto
		// Map (java.util)

		// Instanciando o objeto Map
		Map<String, Double> map = new HashMap<String, Double>();

		// Adicionando os valores
		map.put("key1", 1.0);
		map.put("key2", -23.45e67);

		// Utilizando o construtor da classe para criar o JSONObject
		JSONObject example = new JSONObject(map);
		System.out.println("Final JSONOBject: " + example);
	}

	// Usando JSONWriter
	private static void JSONExamplWriter() {

		// esse método funciona de uma forma similar ao JSONObject e ao JSONStringer
		// A diferença é que ele precisa de um objeto java chamado "Appendable" como o
		// StringBuilder
		StringBuilder sbObj = new StringBuilder();

		JSONWriter jsonWriter = new JSONWriter(sbObj);

		// O comportamento é o mesmo do Stringer

		jsonWriter.object();

		jsonWriter.key("trueValue").value(true);
		jsonWriter.key("falseValue").value(false);
		jsonWriter.key("nullValue").value(null);
		jsonWriter.key("stringValue").value("hello world!");
		jsonWriter.key("complexStringValue").value("h\be\tllo w\u1234orld!");
		jsonWriter.key("intValue").value(42);
		jsonWriter.key("doubleValue").value(-23.45e67);

		jsonWriter.endObject();

		// O resultado está no objeto "write"

		System.out.println("JSON: write" + sbObj.toString());

		// Diferentemente, nós não temos um JSONObject nesse método.

	}

	private static void jsonExampleTokener() {

		// A partir de uma string, podemos utilizar o JSONTokener para criar um array ou
		// um objeto.
		// É necessário deixar a String válida para ambos casos

		String stringObj = "{\"0\":\"value\",\"1\":5,\"2\":-2.345E68,\"3\":true}";
		String stringArray = "[{\"0\":\"value\",\"1\":5,\"2\":-2.345E68,\"3\":true}]";
		// String string = "this is not a valid JSON string";

		// Criando o objeto JSON
		JSONTokener token = new JSONTokener(stringObj);
		JSONObject object = new JSONObject(token);
		System.out.println(object);

		// Criando o array JSON
		token = new JSONTokener(stringArray);
		JSONArray array = new JSONArray(token);
		System.out.println(array);

	}

//Os métodos a seguir são métodos de conversão
//Não é necessário um documento json para trabalhar 
//É possível fazer a conversão através de todo tipo de arquivo
//Também é possível converter o json em todos esses arquivos 

	// Conversão para JSONArray
	private static void JSONObjectToArray() {

		// Criando um JSONObject através de uma string
		// string com a chave e valor
		String string = "{\"0\":\"value\",\"1\":5,\"2\":-2.345E68,\"3\":true}";
		System.out.println(string);

		JSONObject example = new JSONObject(string);
		System.out.println(example);

		// pegando as chaves
		JSONArray keyStrings = listNumberArray(example.length());
		System.out.println(keyStrings);

		// Convertendo para array (só os valores)
		JSONArray array = example.toJSONArray(keyStrings);

		System.out.println("Final JSONArray: " + array);
	}


	//Conversoes Json em XML
	private static void XMLToExampleConversion() {

		// Criando um JSONObject através de uma string
		// string com a chave e valor
		String string = "{\"0\":\"value\",\"1\":5,\"2\":-2.345E68,\"3\":true}";
		JSONObject example = new JSONObject(string);

		// Usando o método toString da classe xml, obtemos o resultado
		String output = XML.toString(example);
		System.out.println("Final XML: " + output);
	}

	//Convertende de xml pra json
	private static void XMLFromExampleConversion() {

		// Começando com uma string xml
		String string = "<0>value</0><1>5</1><2>-2.345E+68</2><3>true</3>";

		// Usando o método toJSONOBject() da classe XML, obtemos o resultado
		JSONObject output = XML.toJSONObject(string);

		System.out.println("Final JSONObject: " + output);
	}

	//Cookie para json</h3>
	private static void CookieToExampleConversion() {

		//é necessário setar o nome do cookie ao construir o JSONObject
		String string = "{\"name\":\"Cookie-Name\",\"value\":\"name\",\"1\":5,\"2\":-2.345E68,\"3\":'true'}";
		JSONObject example = new JSONObject(string);

		// Com o método toString() da classe Cookie, é possível pegar o conteúdo do cookie
		String output = Cookie.toString(example);
		System.out.println("Final Cookie: " + output);
	}

	//convertendo de cookie para json
	private static void CookieFromExampleConversion() {

		// Stringo com o formato do cookie
		String string = "Cookie-Name=name;1=5;2=-2.345E%2b68;3=true";

		// Obtendo o JSONObject através do método toJSONOBject()
		JSONObject output = Cookie.toJSONObject(string);
		System.out.println("Final JSONObject: " + output);
	}

	//Convertendo http
	private static void HTTPToExampleConversion() {

		//Criando o jsonobject através da string http	
		String string = "{\"Method\":\"POST\",\"Request-URI\":'/',\"HTTP-Version\":'HTTP/1.1',\"Value1\":true,\"Value2\":2,\"Value3\":-2.345E68}";

		JSONObject example = new JSONObject(string);

		// Usando o método toString da classe HTTP, obtemos o resultado
		String output = HTTP.toString(example);
		System.out.println("Final HTTP: " + output);
	}

	//convertendo http em json
	private static void HTTPFromExampleConversion() {

		// string com o conteúdo http
		String string = "Final HTTP: POST '/' HTTP/1.1 Value3: -2.345E+68 Value1: true Value2: 2";

		// Obtendo o JSONObject através do método toJSONOBject()
		JSONObject output = HTTP.toJSONObject(string);
		System.out.println("Final JSONObject: " + output);
	}


	// json -> CDL
	private static void CDLToExampleConversion() {

		//Construindo 2 objestod json com as mesmas chaves mas com valores diferentes
		String string = "{\"0\":\"value\",\"1\":5,\"2\":-2.345E68,\"3\":true}";
		JSONObject example = new JSONObject(string);

		String string2 = "{\"0\":\"value2\",\"1\":6,\"2\":-8.345E68,\"3\":false}";
		JSONObject example2 = new JSONObject(string2);

		// colocando os objetos no array
		JSONArray array = new JSONArray();
		array.put(example);
		array.put(example2);

		// A classe CDL mostra o resultado através do método toString
		String output = CDL.toString(array);
		System.out.println("Final CDL: \r\n" + output);
	}

	//CDL -> json
	private static void CDLFromExampleConversion() {

		// Montando uma string no formato CDL
		// Primeiramente colocan-se as chaves...
		// e depois os valores.
		// chaves e valores devem conter o 'escape' \n no final para pular linha
		String string = "0,1,2,3\n" + "value,5,-2.345E+68,true\n" + "value2,6,-8.345E+68,false";

		// Criando o JSONarray
		JSONArray output = CDL.toJSONArray(string);
		System.out.println("Final JSONArray: " + output);
	}


	//json -> properties
	private static Properties PropertyToExampleConversion() {

		// Criando o JSONObject
		String string = "{\"0\":\"value\",\"1\":5,\"2\":-2.345E68,\"3\":true}";
		JSONObject example = new JSONObject(string);

		// String properties através do toString()
		Properties output = Property.toProperties(example);
		System.out.println("Final Properties: " + output);

		return output;
	}

	//properties -> json
	private static void PropertyFromExampleConversion() {

		// criando o objeto properties
		Properties input = PropertyToExampleConversion();

		// criando o objeto json()
		JSONObject output = Property.toJSONObject(input);
		System.out.println("Final JSONObject: " + output);
	}

}
