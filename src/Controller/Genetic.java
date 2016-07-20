package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Fiction
 */
public class Genetic {

    float TotalDisapoitments;
    public ArrayList alreadyAllocateProject = new ArrayList();;


    public float getTotalDisapointments(ArrayList ProjectList, ArrayList StudentList , ArrayList StudentListPreArange, int[][][] ProjectAsinList){





        int[] usedP = new int[ProjectAsinList.length];
        int asinfOrder = 2;


        for(int StudentID=0;ProjectAsinList.length>StudentID;StudentID++){

            alreadyAllocateProject.add(StudentID, ProjectAsinList[StudentID][asinfOrder][1]);


        }

        for(int pID=0;alreadyAllocateProject.size()>pID;pID++){

            System.out.println(pID+1 +". " + getStudentNameByID(StudentList, pID)+ " | " + getProjectNameByID(ProjectList, (int) alreadyAllocateProject.get(pID)));
            TotalDisapoitments += asinfOrder;
        }



        return TotalDisapoitments;
    }



    public int getNumStudentTotalAssinProject( int[][][] ProjectAsinList, int StudentID){

        int TotalProject = 10;

        for(int StudentIDLoop=0;ProjectAsinList.length>StudentIDLoop;StudentIDLoop++){

            if(StudentID==StudentIDLoop){

                for(int projectOrder = 0; 10>projectOrder; projectOrder++){
                    if(ProjectAsinList[StudentID][projectOrder][1]<0){
                        TotalProject =  projectOrder;
                        break;
                    }
                }

            }

        }
        return TotalProject;
    }



    public boolean isInArray(ArrayList ProjectList,String val){

        for(int d=0;ProjectList.size()>d;d++){
            if(ProjectList.get(d).equals(val))
                return true;
        }
        return false;
    }


    public int getProjectIndex(ArrayList ProjectList,String val){

        for(int d=0;ProjectList.size()>d;d++){
            if(ProjectList.get(d).equals(val))
                return d;
        }
        return 0;
    }

    public String getProjectNameByID(ArrayList ProjectList,int ProjectID){

        for(int d=0;ProjectList.size()>d;d++){
            if(d==ProjectID)
                return ProjectList.get(d).toString();
        }
        return "[not found]";
    }
    public String getStudentNameByID(ArrayList StudentList,int StudentID){

        for(int d=0;StudentList.size()>d;d++){
            if(d==StudentID)
                return StudentList.get(d).toString();
        }
        return "[not found]";
    }

    public int getRandomWithExclusion(Random rnd, int start, int end, int[] exclude) {
        int random = rnd.nextInt((end - 0) + 1) + 0;;

        return random;
    }


}