package org.example;

import java.util.ArrayList;
import java.util.List;

class Row {
   public List<Cells> cellsList = new ArrayList<>();

   public int compare(Row a , int index){
      if(this.cellsList.get(index).getIsDate()){
         String[]val1 = this.cellsList.get(index).getValue().split("/");
         String[]val2 = a.cellsList.get(index).getValue().split("/");
         for (int i = 2; i >= 0; i--) {
            int comparison = Integer.compare(Integer.parseInt(val1[i]), Integer.parseInt(val2[i]));
            if (comparison != 0)
               return comparison;
         }
      }
      else
         return this.cellsList.get(index).getValue().compareTo(a.cellsList.get(index).getValue());

      return 0;
   }

}

class Rows{
   public List<Row> rowsList = new ArrayList<>();


   public void cPrint (){
      for(Row row: rowsList){
         System.out.print("{ ");
         for(Cells cell : row.cellsList){
            System.out.print("[ " + cell.getValue() + " ]");
         }
         System.out.println(" }");
      }
   }

   public void sort(int index , int AD) {
      if (AD == 1) {
         for (int i = 1; i < rowsList.size() - 1; i++) {
            for (int j = 1; j < rowsList.size() - i; j++) {
               if (rowsList.get(j).compare(rowsList.get(j + 1), index) > 0) {
                  Row temp = rowsList.get(j);
                  rowsList.set(j, rowsList.get(j + 1));
                  rowsList.set(j + 1, temp);
               }
            }
         }
      }
      else {
         for (int i = 1; i < rowsList.size() - 1; i++) {
            for (int j = 1; j < rowsList.size() - i; j++) {
               if (rowsList.get(j).compare(rowsList.get(j + 1), index) < 0) {
                  Row temp = rowsList.get(j);
                  rowsList.set(j, rowsList.get(j + 1));
                  rowsList.set(j + 1, temp);
               }
            }
         }
      }
   }
}
