import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class csv {

   /**
    * Este método calcula a distância euclidiana entre cada par de pontos de duas
    * matrizes de números reais.
    * 
    * @param X Uma matriz de números reais representando um conjunto de
    *          pontos.
    * @param Y Uma matriz de números reais representando outro conjunto
    *          de pontos.
    * @return Uma matriz de números reais contendo a distância entre
    *         cada par de pontos dos arrays X e Y.
    * @throws IllegalArgumentException Se os arrays X e Y tiverem um número
    *                                  diferente de colunas.
    */
   public static double[][] cdist(double[][] X, double[][] Y) {
      // Obtém o número de linhas e colunas dos arrays X e Y
      int n = X.length;
      int m = X[0].length;
      int p = Y.length;
      int q = Y[0].length;

      // Verifica se as dimensões dos arrays são compatíveis
      if (m != q) {
         throw new IllegalArgumentException("Os arrays devem ter o mesmo número de colunas.");
      }

      // Cria um array bidimensional de double chamado dm para armazenar as distâncias
      double[][] dm = new double[n][p];

      // Usa dois loops for aninhados para percorrer cada par de pontos nos arrays X e
      // Y
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < p; j++) {
            // Inicializa uma variável de double chamada d para armazenar a distância entre
            // os pontos X[i] e Y[j]
            double d = 0;

            // Usa outro loop for para percorrer cada coordenada dos pontos X[i] e Y[j]
            for (int k = 0; k < m; k++) {
               // Calcula a diferença entre as coordenadas e adiciona o quadrado dessa
               // diferença à variável d
               d += Math.pow(X[i][k] - Y[j][k], 2);
            }

            // Calcula a raiz quadrada da variável d e atribui o resultado à posição
            // correspondente no array dm
            dm[i][j] = Math.sqrt(d);
         }
      }

      // Retorna o array dm com as distâncias
      return dm;
   }

   /**
    * sumLines
    * Este método calcula a soma dos elementos de cada linha de uma matriz
    * 
    * @param W Uma matriz de números reais.
    * @return Um vetor de contendo a soma dos elementos de cada linha da matriz W.
    */
   public static double[] sumLines(double[][] W) {
      double[] sum_lines = new double[W.length];
      for (int i = 0; i < W.length; i++) {
         for (int j = 0; j < W[i].length; j++) {
            sum_lines[i] += W[i][j];
         }
      }
      return sum_lines;
   }

   /**
    * calculate_S
    * Esse método é utilizado para calcular a matriz de similaridade entre as
    * linhas da matriz W, normalizando-as pelo seu comprimento.
    * 
    * @param W Matriz a ser calculada a soma das linhas
    * @return D o resultado do método
    */

   public static double[][] calculate_S(double[][] W) {
      double[] d = sumLines(W);
      int nd = d.length;
      double[][] D = new double[nd][nd];
      for (int i = 0; i < nd; i++) {
         for (int j = 0; j < nd; j++) {
            D[i][j] = Math.sqrt(d[i] * d[j]);
         }
      }
      for (int i = 0; i < nd; i++) {
         for (int j = 0; j < nd; j++) {
            D[i][j] = (D[i][j] != 0) ? W[i][j] / D[i][j] : 0;
         }
      }
      return D;
   }

   /**
    * rbf
    * Esse método é utilizado para calcular os pesos de uma rede neural baseada em
    * funções de base radial (RBF), que são funções que dependem apenas da
    * distância entre os pontos de entrada e os centros das funções.
    * 
    * @param X     Matriz a ser calculado os pesos
    * @param sigma
    * @return W resultado da função
    */
   public static double[][] rbf(double[][] X, double sigma) {
      // Obtém o número de linhas e colunas do array X
      int n = X.length;
      int m = X[0].length;

      // Cria um array bidimensional de double chamado W para armazenar os pesos
      double[][] W = new double[n][m];

      // Usa dois loops for aninhados para percorrer cada elemento do array X
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            // Aplica a fórmula da função RBF ao elemento X[i][j] e atribui o resultado à
            // posição correspondente no array W
            W[i][j] = Math.exp(-X[i][j] / (2 * Math.pow(sigma, 2)));
         }
      }
      // Retorna o array W com os pesos
      return W;
   }

   /**
    * fillDiagonal
    * Esse método é usado para modificar uma matriz quadrada, substituindo os
    * elementos da sua diagonal principal por um valor parametrizavel.
    * 
    * @param X     Matriz
    * @param value Valor desejado
    */
   public static void fillDiagonal(double[][] X, double value) {
      // Obtém o número de linhas e colunas do array X
      int n = X.length;
      int m = X[0].length;

      // Verifica se o array é quadrado, ou seja, se tem o mesmo número de linhas e
      // colunas
      if (n != m) {
         throw new IllegalArgumentException("O array deve ser quadrado.");
      }

      // Usa um loop for para percorrer a diagonal principal do array
      for (int i = 0; i < n; i++) {
         // Atribui o valor do segundo argumento à posição correspondente no array
         X[i][i] = value;
      }
   }

   /**
    * mult_matriz
    * Esse método pode ser usado para calcular o produto de duas matrizes reais,
    * multiplicando cada elemento pelo fator alfa.
    * 
    * @param a    Matriz
    * @param b    Matriz
    * @param alfa Fator alfa
    * @return Matriz resultante
    */
   private static double[][] mult_matriz(double[][] a, double[][] b, double alfa) {
      if (a[0].length == b.length) {
         double tmp = 0;
         double[][] c = new double[a.length][b[0].length];
         for (int linha = 0; linha < a.length; linha++) {
            for (int coluna = 0; coluna < b[0].length; coluna++) {
               for (int aux = 0; aux < a[0].length; aux++) {
                  tmp += a[linha][aux] * b[aux][coluna];
               }
               c[linha][coluna] = tmp * alfa;
               tmp = 0;
            }
         }
         return c;
      }
      return null;
   }

   /**
    * Esse método é usado para calcular a iteração de uma função linear sobre uma
    * matriz de entrada, usando um fator de amortecimento alfa.
    * 
    * @param S       Matriz resultante do metodo calculate
    * @param Y_input Matriz de similaridade
    * @param alfa    Fator alfa
    * @param iter    Quantidade de iterações
    * @return Matriz resultante
    */
   public static double[][] iteration(double[][] S, double[][] Y_input, double alfa, int iter) {
      double[][] resp = Y_input;
      for (int it = 0; it < iter; it++) {

         double[][] tmp = mult_matriz(S, resp, alfa);
         if (tmp == null) {
            System.out.println("null");
         }
         double[][] y_tmp = new double[Y_input.length][Y_input[0].length];
         for (int i = 0; i < y_tmp.length; i++) {
            for (int j = 0; j < y_tmp[0].length; j++) {
               y_tmp[i][j] = Y_input[i][j] * (1 - alfa);
            }
         }
         for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
               tmp[i][j] += y_tmp[i][j];
            }
         }
         resp = tmp;
      }

      return resp;
   }

   /**
    * criaCsv
    * Este método cria um arquivo CSV a partir de uma matriz de
    * números reais e um vetor de inteiros.
    * 
    * @param array  Uma matriz de números reais.
    * @param labels Um vetor de inteiros
    * @throws Exception Se ocorrer algum erro ao escrever o arquivo CSV.
    */

   public static void criaCsv(double[][] array, int[] labels) throws Exception {
      BufferedWriter writer = new BufferedWriter(new FileWriter("results.csv"));
      writer.write("x1,x2,y\n");
      for (int i = 0; i < array.length; i++) {
         String tmp = array[i][0] + "," + array[i][1] + "," + labels[i] + "\n";
         writer.write(tmp);
      }
      writer.close();
   }

   /**
    * diag
    * Este método cria uma matriz diagonal a partir de um array.
    * 
    * @param n Um array de números reais.
    * @return Uma matriz diagonal cujos elementos da diagonal principal são os
    *         elementos do array n.
    */

   public static double[][] diag(double[] n) {
      // Declarando o tipo da variável D
      double[][] D = new double[n.length][n.length];

      // Criando uma matriz diagonal com os elementos de sum_lines usando um loop for
      for (int i = 0; i < n.length; i++) {
         D[i][i] = n[i];
      }
      return D;
   }

   public static void main(String[] args) throws Exception {
      // abrir o arquivo CSV
      BufferedReader reader = new BufferedReader(new FileReader("dados.csv"));
      // ler a primeira linha
      String linha = reader.readLine();
      int n = 1000;
      int n_labeled = 20;
      double[][] array = new double[n][2];
      double[] Y = new double[n];
      double[][] Y_input = new double[n][2];
      int i = 0;
      int j = -1;
      int index = 0;
      boolean first = true;
      // enquanto houver linhas no arquivo
      linha = reader.readLine();
      while (linha != null) {
         // separar os valores por vírgula
         String tmp[] = linha.split(",");
         array[i][0] = Double.parseDouble(tmp[0]);
         array[i][1] = Double.parseDouble(tmp[1]);
         Y[i++] = Integer.parseInt(tmp[2]);
         linha = reader.readLine();
      }
      // fechar o arquivo
      reader.close();

      for (int k = 0; k < n_labeled; k++) {
         Y_input[k][(int) Y[k]] = 1;
      }

      // Primeiro passo
      double[][] dm = cdist(array, array);
      double sigma = 0.2;
      double[][] W = rbf(dm, sigma);
      fillDiagonal(W, 0);
      // Segundo passo
      double[][] S = calculate_S(W);
      // Terceiro e quarto passo
      double[][] iter = iteration(S, Y_input, 0.99, 5);
      int[] labels = new int[n];
      for (int ii = 0; ii < n; ii++) {
         if (iter[ii][0] > iter[ii][1]) {
            labels[ii] = 0;
         } else {
            labels[ii] = 1;
         }
      }
      criaCsv(array, labels);
      double acertos = 0;
      for (int ii = 0; ii < n; ii++) {
         if (labels[ii] == (int) Y[ii]) {
            acertos++;
         }
      }
      System.out.println(acertos);
      System.out.println(acertos / n);
   }
}
