package ca.mcmaster.magarveylab.prism.cluster.reactions.typeII;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

import ca.mcmaster.magarveylab.enums.domains.DomainType;
import ca.mcmaster.magarveylab.enums.domains.TypeIIPolyketideDomains;
import ca.mcmaster.magarveylab.prism.cluster.reactions.Reaction;
import ca.mcmaster.magarveylab.prism.cluster.reactions.GenericReaction;
import ca.mcmaster.magarveylab.prism.cluster.reactions.UtilityReactions;
import ca.mcmaster.magarveylab.prism.data.Cluster;
import ca.mcmaster.magarveylab.prism.data.reactions.ReactionPlan;
import ca.mcmaster.magarveylab.prism.data.structure.Residue;
import ca.mcmaster.magarveylab.prism.data.structure.Scaffold;
import ca.mcmaster.magarveylab.prism.util.exception.NoResidueException;
import ca.mcmaster.magarveylab.prism.util.exception.ScaffoldGenerationException;
import ca.mcmaster.magarveylab.prism.util.exception.TailoringSubstrateException;

public class AngucyclineCyclaseReaction extends GenericReaction implements Reaction {
	
	public AngucyclineCyclaseReaction(ReactionPlan plan, Scaffold scaffold, Cluster cluster) {
		super(plan, scaffold, cluster);
		this.domains = new DomainType[] { TypeIIPolyketideDomains.CYCLASE_CLADE_4,
				TypeIIPolyketideDomains.CYCLASE_CLADE_5a };
	}
	
	public void execute() throws NoResidueException, TailoringSubstrateException, ScaffoldGenerationException {
		IAtomContainer molecule = scaffold.molecule();
		
		Residue r1 = scaffold.residue(plan.get(0));
		Residue r2 = scaffold.residue(plan.get(1));
		Residue r3 = scaffold.residue(plan.get(2));
		Residue r4 = scaffold.residue(plan.get(3));
		Residue r5 = scaffold.residue(plan.get(4));
		
		IAtom c1 = r1.ketone();
		IAtom c2 = r1.alphaCarbon();
		IAtom c4 = r2.alphaCarbon();
		IAtom c15 = r3.ketone();
		IAtom c16 = r3.alphaCarbon();
		IAtom c17 = r4.ketone();
		IAtom c19 = r5.ketone();
		
		// add double bond between C4-C17
		UtilityReactions.addBond(c17, c4,  molecule, IBond.Order.DOUBLE);
		
		// add bond between C2-C19
		UtilityReactions.addBond(c19, c2, molecule);
				
		// remove ketone from C17
		UtilityReactions.removeKetone(c17, molecule);

		// reduce ketones at C15, C19
		UtilityReactions.reduceKetone(c15, molecule);
		UtilityReactions.reduceKetone(c19, molecule);

		// set double bond between C15-C16
		UtilityReactions.setBondOrder(c15, c16, molecule, IBond.Order.DOUBLE);
		
		// decarboxylation of C1
		UtilityReactions.decarboxylate(c1, c2, molecule);
	}

}
