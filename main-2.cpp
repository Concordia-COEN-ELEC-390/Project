/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>

using namespace std;

int main()
{
    
    for (int i=0; i<99; i++ )
    {
    float alclevel = static_cast <float> (rand()) / (static_cast <float> (RAND_MAX/0.3));
    if (alclevel < 0.08)
    {
        
        cout<<"Your BAL is "<<alclevel<<"! Yay! You are legally able to drive"<<endl;
    }
    
    else 
    {
        cout<<"Your BAL is "<<alclevel<<"! Opps! You are NOT legally able to drive"<<endl;
    }
    
    }
    
    return 0;
}
