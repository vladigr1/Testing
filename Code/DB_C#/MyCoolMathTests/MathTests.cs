using Microsoft.VisualStudio.TestTools.UnitTesting;
using MyCoolMath;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyCoolMath.Tests
{
    [TestClass()]
    public class MathTests
    {
        private TestContext testContextInstance;
        public TestContext TestContext
        {
            get { return testContextInstance; }
            set { testContextInstance = value; }
        }

        [DataSource("MyExcelDataSource"), TestMethod()]
        
        public void MyTestMethodEXCEL()
        {
            var target = new Math();
            // Access the data
            int x = Convert.ToInt32(TestContext.DataRow["int1"]);
            int y = Convert.ToInt32(TestContext.DataRow["int2"]);
            var expected = Convert.ToInt32(TestContext.DataRow["sum"]);
            var actual = target.Add(x, y);
            Assert.AreEqual(expected, actual,
                "element x:<{0}> y:<{1}>",
                new object[] { x, y });
        }
        [DataSource("MyExcelDataSource"), TestMethod()]
        public void MyTestMethodEXCEL_divide()
        {
            var target = new Math();
            // Access the data
            double x = Convert.ToDouble(TestContext.DataRow["int1"]);
            double y = Convert.ToDouble(TestContext.DataRow["int2"]);
            var expected = TestContext.DataRow["divide"];
            try
            {
                var actual = target.Divide(x, y);
                Assert.AreEqual(expected, actual, "element x:<{0}> y:<{1}>", new object[] { x, y });
            }
            catch (DivideByZeroException e)
            {
                StringAssert.Contains(e.Message,target.DivideOnZero);
            }
            
        }

        // For restoring DB use the following command (in cmd):
        // SQLCMD -E -S .\sqlexpress -Q "RESTORE DATABASE MathDB FROM DISK='MathDB.bak'"
        [DataSource("System.Data.SqlClient", "Data Source=(localdb)\\mssqllocaldb;Initial Catalog = TestData; Integrated Security = True", "dbo.TestData", DataAccessMethod.Sequential), TestMethod()]
        public void AddTest_SQL()
        {
            var target = new Math();

            // Access the data
            //var x = Convert.ToDouble(TestContext.DataRow["val_one"]);
            //var y = Convert.ToDouble(TestContext.DataRow["val_two"]);
            //var expected = Convert.ToDouble(TestContext.DataRow["add_res"]);
            var x = Convert.ToDouble(TestContext.DataRow["value1"]);
            var y = Convert.ToDouble(TestContext.DataRow["value2"]);
            var expected = Convert.ToDouble(TestContext.DataRow["res"]);
            var actual = target.Add(x, y);
            Assert.AreEqual(expected, actual, 0.0001,
                "x:<{0}> y:<{1}>",
                new object[] { x, y });

        }
                       
        [DataSource("System.Data.OleDb", "Provider=Microsoft.ACE.OLEDB.12.0;Data Source=|DataDirectory|\\MathTestDB.accdb", "TestData", DataAccessMethod.Sequential), TestMethod()]
        public void AddTest_ACCESS()
        {
            var target = new Math();

            // Access the data
            var x = Convert.ToDouble(TestContext.DataRow["FirstNumber"]);
            var y = Convert.ToDouble(TestContext.DataRow["SecondNumber"]);
            var expected = Convert.ToDouble(TestContext.DataRow["Sum"]);
            var actual = target.Add(x, y);
            Assert.AreEqual(expected, actual, 0.0001,
                "x:<{0}> y:<{1}>",
                new object[] { x, y });

        }

        //[DeploymentItem("Lab-2Test\\MathTestDB.accdb")]
        [DataSource("MyJetDataSource"), TestMethod()]
        public void MyTestMethodACCESS()
        {
            var target = new Math();
            // Access the data
            int x = Convert.ToInt32(TestContext.DataRow["FirstNumber"]);
            int y = Convert.ToInt32(TestContext.DataRow["SecondNumber"]);
            var expected = Convert.ToInt32(TestContext.DataRow["Sum"]);
            var actual = target.Add(x, y);
            Assert.AreEqual(expected, actual,
                "x:<{0}> y:<{1}>",
                new object[] { x, y });
        }

       /* [TestMethod()]
        public void SubstractTest()
        {
            Assert.Fail();
        }

        [TestMethod()]
        public void MultiplayTest()
        {
            Assert.Fail();
        }
        
        [TestMethod()]
        public void DivideTest()
        {
            Assert.Fail();
        }*/
    }
}