package package2;

import package1.AppraisalWorkItemDTO;
import package1.WorkItemDTO;

/**
 * Created by usegutierrez on 11/19/15.
 */
public class TestImmutable {


    public static void main(String[] args) {
        AppraisalWorkItemDTO appraisalWorkItemDTO = new AppraisalWorkItemDTO();
        appraisalWorkItemDTO.setName("client");


        System.out.println(appraisalWorkItemDTO.getName());
        System.out.println("isA? : " + appraisalWorkItemDTO.isA());
        System.out.println("isB? : " + appraisalWorkItemDTO.isB());


        WorkItemDTO w = appraisalWorkItemDTO;

        System.out.println(w.getName());
        System.out.println("isA? : " + w.isA());
        System.out.println("isB? : " + w.isB());

    }
}
