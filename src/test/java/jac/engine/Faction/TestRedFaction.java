package jac.engine.Faction;

import jac.Enum.SocialAreas;

public class TestRedFaction extends TestFaction{

	public TestRedFaction() {
		
		super(new TestFaction
				.Builder("./FactionsbyBlueFlux/red/RED.txt")
				.addSocialTest(SocialAreas.MORALE, 1)
				.addSocialTest(SocialAreas.RESEARCH, 1)
				.addSocialTest(SocialAreas.ECONOMY, -2)
				.numberOfTechs(1)
				.addFreeTech("InfNet")
				.build()
				);
		// TODO Auto-generated constructor stub
	}

	

}
