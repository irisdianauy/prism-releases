package ca.mcmaster.magarveylab.prism.cluster.annotation.typeII;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openscience.cdk.exception.InvalidSmilesException;

import ca.mcmaster.magarveylab.enums.domains.DomainType;
import ca.mcmaster.magarveylab.enums.domains.TypeIIPolyketideDomains;
import ca.mcmaster.magarveylab.prism.cluster.annotation.Annotator;
import ca.mcmaster.magarveylab.prism.data.Cluster;
import ca.mcmaster.magarveylab.prism.data.Domain;
import ca.mcmaster.magarveylab.prism.data.Module;
import ca.mcmaster.magarveylab.prism.data.reactions.SubstrateSet;

public class C11C12Annotator implements Annotator {
	
	public DomainType[] domains() {
		return new DomainType[] { 
				TypeIIPolyketideDomains.C11KR, 
				TypeIIPolyketideDomains.C11OMT_1, 
				TypeIIPolyketideDomains.C11OMT_2 };
	}

	public List<SubstrateSet> findSubstrates(Domain domain, List<Module> permutation, Cluster cluster) 
			throws InvalidSmilesException, IOException {
		List<SubstrateSet> substrates = new ArrayList<SubstrateSet>(); 
		if (permutation.size() < 6)
			return substrates;
		Module c11 = permutation.get(5);
		SubstrateSet substrate = new SubstrateSet(c11);
		substrates.add(substrate);
		return substrates;
	}
		
}
