import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthDesktopIconUI;

public class csv {

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



   public static void main(String[] args) throws Exception {
      // abrir o arquivo CSV
      BufferedReader reader = new BufferedReader(new FileReader("moons.csv"));
      // ler a primeira linha
      String linha = reader.readLine();
      double[][] array = new double[1000][2];
      int i = -1;
      int j = -1;
      int index = 0;
      boolean first = true;
      // enquanto houver linhas no arquivo
      while (linha != null) {
         // separar os valores por vírgula
         StringTokenizer tokens = new StringTokenizer(linha, ",");
         // inicializa o obj X
         linha = reader.readLine();
         // imprimir os valores na tela
         while (tokens.hasMoreTokens()) {
            // System.out.println("i = "+i+" j = "+j);
            if (first == true) {
               tokens.nextToken();

            } else {
               if (index == 0) {
                  array[i][j] = Double.parseDouble(tokens.nextToken());

               } else if (index == 1) {
                  array[i][j] = Double.parseDouble(tokens.nextToken());
               } else {
                  tokens.nextToken();
               }
            }
            j++;
            index++;
         }
         first = false;
         index = 0;
         j = 0;
         i++;
         // ler a próxima linha
         linha = reader.readLine();
      }
      // System.out.println("x0 = "+array[0][0]+" x1 = "+array[0][1]);
      // fechar o arquivo
      reader.close();

      double[][] dm = cdist(array, array);
      /*
       * System.out.println("Matriz de distâncias:");
       * System.out.println(Arrays.deepToString(dm));
       */
      double sigma = 0.2;
      double[][] W  = rbf(dm, sigma);
      //fillDiagonal(W, 0);
      //System.out.println(Arrays.deepToString(W));

      //double[] sum_lines = sumLines(W);

   }
}
