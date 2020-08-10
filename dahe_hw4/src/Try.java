public class Try {
    static char[][] matrix;
    public static void main(String args[]){
        char[][] array2 = new char [3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j <3;j++) {
                array2[i][j] ='a';
            }
        }

        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                System.out.print(array2[row][col]);
            }
            System.out.println();
        }

        String a = "abcdfg";
        int c = 9;
        char b = (char)(c+'0');
        System.out.println(b);
    }
}
