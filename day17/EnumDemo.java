package day17;

public class EnumDemo {
	public static void main(String[] args) {
		Cars car;
		car=Cars.maruti;
		method(car);
	}
public static void method(Cars car)
{
	if(car==Cars.maruti)
	{
		System.out.println("maruti;");
	}
	else if(car==Cars.benz) {
		System.out.println("benz");
	}
	
	
	switch(car){
	case maruti:{
		System.out.println("switch case - its a maruti....");
		break;
	}
	case fiat:{
		System.out.println("switch case- its a fiat....");
	}
	default:{
		System.out.println("unknown..........");
	}
	}
	
	Cars c[]=Cars.values();
	for(Cars cc:c) {
		System.out.println(cc.getPrize());
	}
	Cars.maruti.setPrize(500);
	System.out.println("maruti prize..:"+Cars.maruti.getPrize());

}
}
enum Cars
{
	maruti(1000),skoda(2000),fiat(3000),benz(4000);
	private int prize;
	Cars(int prize)
	{
		this.prize=prize;
	}
	public int getPrize() {
		return prize;
	}
	public void setPrize(int prize) {
		this.prize = prize;
	}
	
}