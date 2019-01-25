
import static org.ethereum.solidity.compiler.SolidityCompiler.Options.ABI;
import static org.ethereum.solidity.compiler.SolidityCompiler.Options.BIN;
import static org.ethereum.solidity.compiler.SolidityCompiler.Options.INTERFACE;
import static org.ethereum.solidity.compiler.SolidityCompiler.Options.METADATA;

import java.io.IOException;
import org.ethereum.core.CallTransaction;
import org.ethereum.solidity.compiler.CompilationResult;
import org.ethereum.solidity.compiler.SolidityCompiler;



public class AchalTest {

  public static void main(String[] args) throws IOException {

    String contractSrc =
        "pragma solidity ^0.4.7;\n"
            + "\n"
            + "contract SimpleStorage {\n"
            + "    uint storedData;\n"
            + "    function set(uint x) {\n"
            + "        storedData = x;\n"
            + "    }\n"
            + "    function get() constant returns (uint retVal) {\n"
            + "        return storedData;\n"
            + "    }\n"
            + "}";

    SolidityCompiler.Result res = SolidityCompiler.compile(
        contractSrc.getBytes(), true, ABI, BIN, INTERFACE, METADATA);
    System.out.println("Out: '" + res.output + "'");
    System.out.println("Err: '" + res.errors + "'");
    CompilationResult result = CompilationResult.parse(res.output);

    System.out.println(result);
    System.out.println(result.getContractName());
    CompilationResult.ContractMetadata a = result.getContract("SimpleStorage");

//    CompilationResult.ContractMetadata a = result.getContract(contractSrc);
    CallTransaction.Contract contract = new CallTransaction.Contract(a.abi);
    System.out.println(contract.functions[0].toString());
    System.out.println(contract.getConstructor());


  }




}
