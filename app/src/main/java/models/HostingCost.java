package models;

public class HostingCost {
    private String customerName;
    private String province;
    private String webSpace;
    private String salesDate;
    double dbCost;
    double stagingCost;
    double planCost;

    public HostingCost(String customerName, String province, String webSpace, String salesDate, double dbCost, double stagingCost, double planCost) {
        this.customerName = customerName;
        this.province = province;
        this.webSpace = webSpace;
        this.salesDate = salesDate;
        this.dbCost = dbCost;
        this.stagingCost = stagingCost;
        this.planCost = planCost;
    }

    public String getHostingCost(){
        double additionalCost =  dbCost+stagingCost;
        double webSpaceCost = 0;
        double totalCost = 0;
        switch (webSpace){
            case "10GB": webSpaceCost = 0;
            break;
            case "20GB": webSpaceCost = 6.5;
            break;
            case "40GB": webSpaceCost = 8.5;
            break;
        }
        totalCost = planCost+ additionalCost+ webSpaceCost;
        return "For "+ customerName + " from "+ province +", total hosting cost is: "
                + String.valueOf(totalCost)+ " Sales Date: "+ salesDate;
    }
}
