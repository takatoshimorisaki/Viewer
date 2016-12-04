package mori.EM;

public class CSource{

	private CVector Pos = new CVector();

	private CVector Vel = new CVector();

	private double Elapsed;

	private double Dt = 1.0;

	private double Amp = 20.0;

	private double Omega = 2.0 * Math.PI / 10.0;

	public void Update(){

		Pos.mX[0] = Amp * Math.sin(Omega * Elapsed);

		Vel.mX[0] = Amp * Math.cos(Omega * Elapsed);

		Elapsed += Dt;
	}

	public CVector GetPos(){
		return Pos;
	}

	public CVector GetVel(){
		return Vel;
	}

}

