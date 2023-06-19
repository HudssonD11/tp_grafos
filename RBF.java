
// Importa a classe Math do pacote java.lang
import java.lang.Math;

// Importa a classe Arrays do pacote java.util
import java.util.Arrays;

// Define uma classe chamada RBF
public class RBF {

   // Define um método estático chamado cdist que recebe dois arrays bidimensionais
   // de double como argumentos
   // Este método calcula a distância entre cada par de pontos nos dois arrays
   // A distância usada é a euclidiana, que é a raiz quadrada da soma dos quadrados
   // das diferenças entre as coordenadas
   // O método retorna um array bidimensional de double com as distâncias
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

   // Define um método estático chamado rbf que recebe dois argumentos: um array
   // bidimensional de double e um double
   // Este método aplica a fórmula da função radial de base gaussiana (RBF) a cada
   // elemento do array
   // A fórmula é e^(-x/(2*sigma^2)), onde e é o número de Euler e ^ é o operador
   // de potência
   // A função RBF é usada para transformar as distâncias em pesos que variam entre
   // 0 e 1
   // Quanto menor a distância, maior o peso, e vice-versa
   // Sigma é um parâmetro que controla a largura da função RBF, ou seja, o quanto
   // ela se espalha
   // O método retorna um array bidimensional de double com os pesos
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

   // Define um método estático chamado fillDiagonal que recebe dois argumentos: um
   // array bidimensional de double e um double
   // Este método preenche a diagonal principal do array com o valor do segundo
   // argumento
   // Isso significa que os pesos dos pontos consigo mesmos são zero, o que faz
   // sentido pois a distância é zero
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

   public static double[][] calculate_S(double[][] W) {
      double[] d = Arrays.stream(W).mapToDouble(row -> Arrays.stream(row).sum()).toArray();
      double[][] D = new double[d.length][d.length];
      for (int i = 0; i < d.length; i++) {
         for (int j = 0; j < d.length; j++) {
            D[i][j] = Math.sqrt(d[i] * d[j]);
         }
      }
      double[][] S = new double[W.length][W[0].length];
      for (int i = 0; i < W.length; i++) {
         for (int j = 0; j < W[0].length; j++) {
            if (D[i][j] != 0) {
               S[i][j] = W[i][j] / D[i][j];
            }
         }
      }
      return S;
   }

   // Define um método principal para testar os métodos anteriores
   public static void main(String[] args) {
      // Cria um array bidimensional de double chamado X com alguns dados de exemplo
      double[][] X = { { 1, 2 }, { 3, 4 }, { 5, 6 } };

      // Calcula a matriz de distâncias dm usando o método cdist
      double[][] dm = cdist(X, X);

      // Imprime o array dm na tela usando o método toString da classe Arrays
      System.out.println("Matriz de distâncias:");
      System.out.println(Arrays.deepToString(dm));

      // Define um valor de sigma para usar na função RBF
      double sigma = 0.2;

      // Calcula a matriz de pesos W usando o método rbf
      double[][] W = rbf(dm, sigma);

      // Imprime o array W na tela usando o método toString da classe Arrays
      System.out.println("Matriz de pesos:");
      System.out.println(Arrays.deepToString(W));

      // Preenche a diagonal principal da matriz W com zeros usando o método
      // fillDiagonal
      fillDiagonal(W, 0);

      double[][] S = calculate_S(W);
      for (double[] row : S) {
         System.out.println(Arrays.toString(row));
      }

   }
}
